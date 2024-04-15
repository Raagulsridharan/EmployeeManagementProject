package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmpRoleSalaryDAO;
import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.Enum.TaxCalculation;
import com.employee.demoproject.service.DesignationService;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

@Repository
public class EmpRoleSalaryDAOImpl implements EmpRoleSalaryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DesignationService designationService;

    @Override
    public List<EmployeePaymentDTO> getAllEmpRoleSalary() {
        List<EmployeePaymentDTO> employeePaymentDTOS = (List<EmployeePaymentDTO>) sessionFactory.getCurrentSession()
                                    .createNativeQuery("select ers.id, e.name, d.name as dept, ds.role, ers.annual_salary_pack\n" +
                                            "from employee e\n" +
                                            "join department d\n" +
                                            "on e.dept_id = d.id\n" +
                                            "join emp_role_salary ers\n" +
                                            "on e.id = ers.employee_id\n" +
                                            "join designations ds\n" +
                                            "on ers.role_id = ds.id;")
                                    .getResultList();
        return employeePaymentDTOS;
    }
    @Override
    public void createEmpRoleSalary(int empId,EmpRoleSalaryDTO empRoleSalaryDTO){
        Employee emp = sessionFactory.getCurrentSession().get(Employee.class,empId);
        EmpRoleSalary empRoleSalary = new EmpRoleSalary();
        empRoleSalary.setEmployee_role_salary(emp);

        empRoleSalary =  assignRoleAndSalary(empRoleSalary,empRoleSalaryDTO);

        sessionFactory.getCurrentSession().persist(empRoleSalary);
    }

    @Override
    public void updateEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO) {
        EmpRoleSalary empRoleSalary = (EmpRoleSalary) sessionFactory.getCurrentSession()
                                                    .createQuery("from EmpRoleSalary ers\n" +
                                                            "where ers.employee_role_salary.id =:empId")
                                                    .setParameter("empId",empId)
                                                    .uniqueResult();
        empRoleSalary =  assignRoleAndSalary(empRoleSalary,empRoleSalaryDTO);
        sessionFactory.getCurrentSession().saveOrUpdate(empRoleSalary);
    }

    @Override
    public EmployeePaymentDTO getRoleSalaryByEmployee(int empId) {
        Query<EmployeePaymentDTO> query = sessionFactory.getCurrentSession()
                                                              .createQuery("select e.id, e.name, d.name as dept, ds.role, ers.basic_sal_month, ers.tax_reduction_month, ers.net_sal_month\n" +
                                                                      "from Employee e\n" +
                                                                      " join Department d\n" +
                                                                      "\ton e.department.id = d.id\n" +
                                                                      " join EmpRoleSalary ers\n" +
                                                                      "\ton e.id = ers.employee_role_salary.id\n" +
                                                                      " join Designation ds\n" +
                                                                      "\ton ers.employee_role_salary.id = ds.id\n" +
                                                                      "where e.id = :empId",EmployeePaymentDTO.class)
                                                              .setParameter("empId",empId);
        EmployeePaymentDTO employeePaymentDTOList = query.uniqueResult();
        return employeePaymentDTOList;
    }

    private EmpRoleSalary assignRoleAndSalary(EmpRoleSalary empRoleSalary, EmpRoleSalaryDTO empRoleSalaryDTO){
        Designation designation = designationService.getDesignationById(empRoleSalaryDTO.getRoleId());
        empRoleSalary.setDesignation(designation);

        System.out.println("Choose salary package between : "+designation.getSalary_package());
        Double salaryPack = empRoleSalaryDTO.getSalaryPack();
        empRoleSalary.setAnnual_salary_pack(salaryPack);

        Double salary = salaryPack*100000;
        Double monthlyTaxReduction = calculateTax(salary)/12;
        empRoleSalary.setTax_reduction_month(monthlyTaxReduction);

        Double basicSalaryMonth = salary/12;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Double formattedResult = Double.parseDouble(decimalFormat.format(basicSalaryMonth));
        empRoleSalary.setBasic_sal_month(formattedResult);

        Double netSalary = formattedResult-monthlyTaxReduction;
        empRoleSalary.setNet_sal_month(netSalary);

        return empRoleSalary;
    }

    private static double calculateTax(double salary) {
        for (TaxCalculation slab : TaxCalculation.values()) {
            if (salary >= slab.getLowerLimit() && salary <= slab.getUpperLimit()) {
                return salary * slab.getPercentage();
            }
        }
        return 0;
    }
}

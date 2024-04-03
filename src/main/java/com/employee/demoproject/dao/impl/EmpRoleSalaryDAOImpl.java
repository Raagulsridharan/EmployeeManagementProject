package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmpRoleSalaryDAO;
import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.Enum.TaxCalculation;
import com.employee.demoproject.service.DesignationService;
import org.hibernate.SessionFactory;
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
    public List<EmpRoleSalary> getAllEmpRoleSalary() {
        return sessionFactory.getCurrentSession().createQuery("SELECT e FROM EmpRoleSalary e").getResultList();
    }
    @Override
    public void createEmpRoleSalary(int empId,EmpRoleSalaryDTO empRoleSalaryDTO){
        Employee emp = sessionFactory.getCurrentSession().get(Employee.class,empId);
        EmpRoleSalary empRoleSalary = new EmpRoleSalary();
           empRoleSalary.setEmployee_role_salary(emp);
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

        sessionFactory.getCurrentSession().persist(empRoleSalary);
    }

    public static double calculateTax(double salary) {
        for (TaxCalculation slab : TaxCalculation.values()) {
            if (salary >= slab.getLowerLimit() && salary <= slab.getUpperLimit()) {
                return salary * slab.getPercentage();
            }
        }
        return 0;
    }
}

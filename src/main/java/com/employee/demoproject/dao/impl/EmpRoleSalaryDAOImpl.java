package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmpRoleSalaryDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.Enum.TaxCalculation;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.DesignationService;
import org.apache.log4j.Logger;
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

    @Autowired
    private DataRetrieve dataRetrieve;

    static Logger logger = Logger.getLogger(EmpRoleSalaryDAOImpl.class);

    @Override
    public List<EmployeePaymentDTO> getAllEmpRoleSalary() {
        String query = "select ers.id, e.id, e.name, d.name as dept, ds.role, ers.annual_salary_pack\n" +
                "from Employee e\n" +
                "join Department d\n" +
                "on e.department.id = d.id\n" +
                "join EmpRoleSalary ers\n" +
                "on e.id = ers.employee_role_salary.id\n" +
                "join Designation ds\n" +
                "on ers.designation.id = ds.id";

        List<EmployeePaymentDTO> employeePaymentDTOS = dataRetrieve.processList(query,EmployeePaymentDTO.class);
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

    @Override
    public Long totalRoleSalaryCount() throws DataServiceException {
        try{
            logger.info("getting count of EMpRoleSalary");
            String query = "Select count(*) From EmpRoleSalary";
            Long count = dataRetrieve.getCount(query);
            return count;
        }catch (DataAccessException e){
            logger.error("Error in getting count of EmpRoleSalary in business layer. "+e);
            throw new DataServiceException("Exception in data accessing in business layer for getting count of EmpRoleSalary",e);
        }
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

    @Override
    public List<EmpRoleSalary> filterEmpRoleSalary(FilterOption filterOption) throws DataServiceException {
        try {
            logger.info("Entering the method of fetch EmpRoleSalary by filtering");
            Integer firstResult = (filterOption.getPageNo() * filterOption.getPageSize()) - filterOption.getPageSize();

            StringBuilder queryParam = new StringBuilder("FROM EmpRoleSalary ers");
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                queryParam.append(" WHERE ers.employee_role_salary.name LIKE :searchKey1 OR ers.employee_role_salary.department.name LIKE :searchKey2 OR ers.designation.role LIKE :searchKey3");
            }

            Query query = sessionFactory.getCurrentSession().createQuery(queryParam.toString());
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                query.setParameter("searchKey1", "%" + filterOption.getSearchKey() + "%")
                        .setParameter("searchKey2", "%" + filterOption.getSearchKey() + "%")
                        .setParameter("searchKey3", "%" + filterOption.getSearchKey() + "%");
            }
            query.setFirstResult(firstResult);
            query.setMaxResults(filterOption.getPageSize());

            List<EmpRoleSalary> empRoleSalaryList = query.list();

            return empRoleSalaryList;
        } catch (Exception e) {
            logger.error("Error found in filter EmpRoleSalary. "+e);
            throw new DataServiceException("Exception in accessing the EmpRoleSalary for filtering", e);
        }
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

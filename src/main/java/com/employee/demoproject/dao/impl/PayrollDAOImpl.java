package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.PayrollDAO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.Payroll;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PayrollDAOImpl implements PayrollDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PayrollDTO> getEmployeePayroll(int empId) {
        List<PayrollDTO> payrolls = sessionFactory.getCurrentSession()
                                                  .createQuery("select e.id, e.name, ers.net_sal_month, p.month, p.paid_salary, p.description, p.status \n" +
                                                          "from Employee e\n" +
                                                          " join Department d\n" +
                                                          "\ton e.department.id = d.id \n" +
                                                          " join EmpRoleSalary ers\n" +
                                                          "\ton e.id = ers.employee_role_salary.id \n" +
                                                          " join Designation ds \n" +
                                                          "\ton ers.designation.id = ds.id \n" +
                                                          " join Payroll p \n" +
                                                          "\ton p.empRoleSalary_payroll.id = ers.id \n" +
                                                          "where e.id = :empId")
                                                  .setParameter("empId",empId)
                                                  .getResultList();
        return payrolls;
    }

    @Override
    public List<EmployeePaymentDTO> getAllEmployeePayroll() {
        List<EmployeePaymentDTO> empPayrolls = sessionFactory.getCurrentSession()
                                                          .createQuery("select distinct e.id, e.name, d.name as dept, ds.role, ers.basic_sal_month, ers.tax_reduction_month, ers.net_sal_month \n" +
                                                                  "from Employee e \n" +
                                                                  " join Department d \n" +
                                                                  "\ton e.department.id = d.id\n" +
                                                                  " join EmpRoleSalary ers \n" +
                                                                  "\ton e.id = ers.employee_role_salary.id \n" +
                                                                  " join Designation ds \n" +
                                                                  "\ton ers.designation.id = ds.id\n" +
                                                                  " join Payroll p \n" +
                                                                  "\ton p.empRoleSalary_payroll.id = ers.id",EmployeePaymentDTO.class)
                                                          .getResultList();
        return empPayrolls;
    }

    @Override
    public void makePayment(int empId, PayrollDTO payrollDTO) {
        EmpRoleSalary empRoleSalary = (EmpRoleSalary) sessionFactory.getCurrentSession()
                                         .createQuery("select ers\n" +
                                                 "from EmpRoleSalary ers\n" +
                                                 "where ers.employee_role_salary.id = :empId")
                                         .setParameter("empId",empId)
                                         .uniqueResult();

        Payroll payroll = new Payroll();
        payroll.setEmpRoleSalary_payroll(empRoleSalary);
        payroll.setMonth(payrollDTO.getMonth());
        payroll.setPaid_salary(empRoleSalary.getNet_sal_month());
        payroll.setDescription(payrollDTO.getDescription());
        payroll.setStatus("Paid");

        sessionFactory.getCurrentSession().persist(payroll);
        System.out.println("Salary credited...");
    }

    @Override
    public void createPayroll(int empId) {
        EmpRoleSalary empRoleSalary = (EmpRoleSalary) sessionFactory.getCurrentSession()
                .createQuery("select ers\n" +
                        "from EmpRoleSalary ers\n" +
                        "where ers.employee_role_salary.id = :empId")
                .setParameter("empId",empId)
                .uniqueResult();

        Payroll payroll = new Payroll();
        payroll.setEmpRoleSalary_payroll(empRoleSalary);

        LocalDate currentDate = LocalDate.now();
        String month = currentDate.getMonth().toString() +","+ String.valueOf(currentDate.getYear());
        payroll.setMonth(month);
        payroll.setPaid_salary(0.00);
        payroll.setDescription("Let's pay salary...!");
        payroll.setStatus("Not Paid");

        sessionFactory.getCurrentSession().persist(payroll);
        System.out.println("Payroll created...");
    }
}

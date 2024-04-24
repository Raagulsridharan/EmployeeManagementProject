package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.PayrollDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.Payroll;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PayrollDAOImpl implements PayrollDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataRetrieve dataRetrieve;

    @Override
    public List<PayrollDTO> getEmployeePayroll(int empId) {
        String query = "select p.id, e.id, e.name, ers.net_sal_month, p.month, p.paid_salary, p.description, p.status \n" +
                "from Employee e\n" +
                " join Department d\n" +
                "\ton e.department.id = d.id \n" +
                " join EmpRoleSalary ers\n" +
                "\ton e.id = ers.employee_role_salary.id \n" +
                " join Designation ds \n" +
                "\ton ers.designation.id = ds.id \n" +
                " join Payroll p \n" +
                "\ton p.empRoleSalary_payroll.id = ers.id \n" +
                "where e.id = :id";

        List<PayrollDTO> payrolls = dataRetrieve.getListById(query,empId,PayrollDTO.class);
        return payrolls;
    }

    @Override
    public List<EmployeePaymentDTO> getAllEmployeePayroll() {

        String query = "select distinct e.id, e.name, d.name as dept, ds.role, ers.annual_salary_pack ,ers.basic_sal_month, ers.tax_reduction_month, ers.net_sal_month \n" +
                "from Employee e \n" +
                " join Department d \n" +
                "\ton e.department.id = d.id\n" +
                " join EmpRoleSalary ers \n" +
                "\ton e.id = ers.employee_role_salary.id \n" +
                " join Designation ds \n" +
                "\ton ers.designation.id = ds.id\n" +
                " join Payroll p \n" +
                "\ton p.empRoleSalary_payroll.id = ers.id";
        List<EmployeePaymentDTO> empPayrolls = dataRetrieve.processList(query,EmployeePaymentDTO.class);
        return empPayrolls;
    }

    @Override
    public Payroll makePayment(int empId, PayrollDTO payrollDTO) {

        String query =  "from EmpRoleSalary ers\n" +
                        "where ers.employee_role_salary.id = :id";
        EmpRoleSalary empRoleSalary = dataRetrieve.getObjectById(query,empId,EmpRoleSalary.class);

        Payroll payroll = new Payroll();
        payroll.setEmpRoleSalary_payroll(empRoleSalary);
        payroll.setMonth(payrollDTO.getMonth());
        payroll.setPaid_salary(empRoleSalary.getNet_sal_month());
        payroll.setDescription(payrollDTO.getDescription());
        payroll.setStatus("Paid");

        sessionFactory.getCurrentSession().save(payroll);
        System.out.println("Salary credited...");
        return payroll;
    }

    @Override
    public Payroll createPayroll(int empId) {
        String query = "from EmpRoleSalary ers\n" +
                "where ers.employee_role_salary.id = :id";
        EmpRoleSalary empRoleSalary = dataRetrieve.getObjectById(query,empId,EmpRoleSalary.class);

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
        return payroll;
    }

    @Override
    public Payroll updatePayroll(int empId, PayrollDTO payrollDTO) {

        String query =  "from EmpRoleSalary ers\n" +
                        "where ers.employee_role_salary.id = :id";
        EmpRoleSalary empRoleSalary = dataRetrieve.getObjectById(query,empId,EmpRoleSalary.class);


        Payroll payroll = sessionFactory.getCurrentSession().get(Payroll.class,payrollDTO.getId());
        payroll.setPaid_salary(empRoleSalary.getNet_sal_month());
        payroll.setDescription(payrollDTO.getDescription());
        payroll.setStatus("Paid");

        sessionFactory.getCurrentSession().saveOrUpdate(payroll);
        System.out.println("Payroll updated...");
        return payroll;
    }

    @Override
    public PaySlipDTO getPAYSlipContent(Integer payrollId) {
        String query = "select e.id,e.name,dep.name as dept, d.role, ers.annual_salary_pack, ers.basic_sal_month, ers.tax_reduction_month, ers.net_sal_month ,p.id, p.month \n" +
                "from Payroll p \n" +
                "join EmpRoleSalary ers \n" +
                "on p.empRoleSalary_payroll.id = ers.id\n" +
                "join Employee e \n" +
                "on e.id = ers.employee_role_salary.id\n" +
                "join Designation d \n" +
                "on ers.designation.id = d.id\n" +
                "join Department dep \n" +
                "on e.department.id = dep.id\n" +
                "where p.id = :id";

        PaySlipDTO paySlipDTO = dataRetrieve.getObjectById(query,payrollId,PaySlipDTO.class);
        return paySlipDTO;
    }

    @Override
    public Payroll getPaySlip(int payrollId) {
        Payroll payroll = sessionFactory.getCurrentSession().get(Payroll.class,payrollId);
        //byte[] paySlip = payroll.getPaySlip();
        return payroll;
    }

    @Override
    public void uploadPaySlip(Integer payrollId, MultipartFile file){
        String fileName = file.getOriginalFilename();
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Payroll payroll = sessionFactory.getCurrentSession().get(Payroll.class,payrollId);
        payroll.setPaySlip(fileBytes);
        sessionFactory.getCurrentSession().saveOrUpdate(payroll);

    }

    @Override
    public void generatePaySlip() {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Paragraph paragraph = new Paragraph("Content");
            document.add(paragraph);
            document.close();

            byte[] pdfBytes = outputStream.toByteArray();
            Payroll payroll = sessionFactory.getCurrentSession().get(Payroll.class,2);
            payroll.setPaySlip(pdfBytes);
            sessionFactory.getCurrentSession().saveOrUpdate(payroll);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


}

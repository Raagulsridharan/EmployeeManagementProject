package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.PayrollDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.Payroll;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
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

    static Logger logger = Logger.getLogger(PayrollDAOImpl.class);

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
    public EmployeePaymentDTO getEmployeeSalaryDetails(int empId) {
        String query = "select distinct e.id, e.name, d.name as dept, ds.role, ers.annual_salary_pack ,ers.basic_sal_month, ers.tax_reduction_month, ers.net_sal_month \n" +
                "from Employee e \n" +
                " join Department d \n" +
                "\ton e.department.id = d.id\n" +
                " join EmpRoleSalary ers \n" +
                "\ton e.id = ers.employee_role_salary.id \n" +
                " join Designation ds \n" +
                "\ton ers.designation.id = ds.id\n" +
                " join Payroll p \n" +
                "\ton p.empRoleSalary_payroll.id = ers.id where e.id = :id";
        EmployeePaymentDTO empPayrolls = dataRetrieve.getObjectById(query,empId,EmployeePaymentDTO.class);
        return empPayrolls;
    }

    @Override
    public Long totalPayrollCount() throws DataServiceException {
        try {
            logger.info("Business layer for getting count of total payroll.");
            String query = "SELECT COUNT(*)\n" +
                    "FROM (\n" +
                    "    SELECT COUNT(*) AS payroll_count\n" +
                    "    FROM Payroll\n" +
                    "    GROUP BY empRoleSalary_payroll\n" +
                    ") AS subquery_result";
            Long count = dataRetrieve.getCount(query);
            return count;
        }catch (DataAccessException e){
            logger.error("Error in Business layer for getting count of total payroll. "+e);
            throw new DataServiceException("Exception in Getting total payroll count",e);
        }
    }

    @Override
    public List<Payroll> filterPayroll(FilterOption filterOption) throws DataServiceException {
        try {
            logger.info("Entering the method of fetch payroll by filtering");
            Integer firstResult = (filterOption.getPageNo() * filterOption.getPageSize()) - filterOption.getPageSize();

            StringBuilder queryParam = new StringBuilder("FROM Payroll p ");
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                queryParam.append(" WHERE p.empRoleSalary_payroll.employee_role_salary.name LIKE :searchKey1 OR p.empRoleSalary_payroll.employee_role_salary.department.name LIKE :searchKey2 OR p.empRoleSalary_payroll.designation.role LIKE :searchKey3");
            }

            Query query = sessionFactory.getCurrentSession().createQuery(queryParam.toString());
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                query.setParameter("searchKey1", "%" + filterOption.getSearchKey() + "%")
                        .setParameter("searchKey2", "%" + filterOption.getSearchKey() + "%")
                        .setParameter("searchKey3", "%" + filterOption.getSearchKey() + "%");
            }
            query.setFirstResult(firstResult);
            query.setMaxResults(filterOption.getPageSize());

            List<Payroll> payrolls = query.list();

            return payrolls;
        } catch (Exception e) {
            logger.error("Error found in filter payroll"+e);
            throw new DataServiceException("Exception in accessing the payroll for filtering", e);
        }
    }



    //---------------------------------

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

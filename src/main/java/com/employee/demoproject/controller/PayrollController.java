package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.Payroll;
import com.employee.demoproject.service.PDFExporter;
import com.employee.demoproject.service.PayrollService;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payroll")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/getEmployeePayroll/{empId}")
    public ResponseEntity<List<PayrollDTO>> getEmployeePayroll(@PathVariable int empId){
        return new ResponseEntity<>(payrollService.getEmployeePayroll(empId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeePaymentDTO>> getEmployeePayroll(){
        return new ResponseEntity<>(payrollService.getAllEmployeePayroll(), HttpStatus.OK);
    }

    @PostMapping("/makePayment/{empId}")
    public String makePayment(@PathVariable int empId, @RequestBody PayrollDTO payrollDTO){
        payrollService.makePayment(empId,payrollDTO);
        return "Salary credited...!!!";
    }

    @PostMapping("/createPayroll/{empId}")
    public String createPayroll(@PathVariable int empId){
        payrollService.createPayroll(empId);
        return "Payroll created...!";
    }

    @GetMapping("/getPaySlipcontent/{salaryId}")
    public ResponseEntity<PayrollDTO> getPaySlipContent(@PathVariable Integer salaryId){
        return new ResponseEntity(payrollService.getPAYSlipContent(salaryId),HttpStatus.OK);
    }

    @GetMapping("/exportPDF/{salaryId}")
    public void exportToPDF(@PathVariable Integer salaryId,HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        PaySlipDTO paySlipDTO = payrollService.getPAYSlipContent(salaryId);

        PDFExporter exporter = new PDFExporter(paySlipDTO);
        exporter.export(response);
    }





    @GetMapping("/download/payslip/{payslipId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer payslipId){
        Payroll payroll = payrollService.getPaySlip(payslipId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("pdf/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment: fileName=\""+payroll.getMonth()+"\"")
                .body(payroll.getPaySlip());
    }

    @PostMapping("/upload/payslip/{payrollId}")
    public String uploadPayslip(@PathVariable Integer payrollId, @RequestParam("multipartFile") MultipartFile multipartFile){
        payrollService.uploadPaySlip(payrollId,multipartFile);
        return "Successfully uploaded";
    }

    @PostMapping("/generate/paySlip")
    public String generatePaySlip(){
        payrollService.generatePaySlip();
        return "Generated";
    }
}

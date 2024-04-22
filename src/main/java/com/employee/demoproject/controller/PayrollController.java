package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.HttpStatusEntity;
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

    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusEntity> getEmployeePayroll(@PathVariable int empId){
        return ResponseEntity.ok(new HttpStatusEntity(payrollService.getEmployeePayroll(empId), HttpStatus.OK.value(),"Payroll for employee"));
    }

    @GetMapping
    public ResponseEntity<HttpStatusEntity> getAllEmployeePayroll(){
        return ResponseEntity.ok(new HttpStatusEntity(payrollService.getAllEmployeePayroll(), HttpStatus.OK.value(),"Payroll of All the employees"));
    }

    @PostMapping("/{empId}")
    public ResponseEntity<HttpStatusEntity> makePayment(@PathVariable int empId, @RequestBody PayrollDTO payrollDTO){
        return ResponseEntity.ok(new HttpStatusEntity(payrollService.makePayment(empId,payrollDTO),HttpStatus.OK.value(), "Successfully payment done"));
    }

    @PostMapping("/createPayroll/{empId}")
    public ResponseEntity<HttpStatusEntity> createPayroll(@PathVariable int empId){
        return ResponseEntity.ok(new HttpStatusEntity(payrollService.createPayroll(empId),HttpStatus.OK.value(),"Payroll created...!"));
    }

    @GetMapping("/getPaySlipcontent/{payrollId}")
    public ResponseEntity<PayrollDTO> getPaySlipContent(@PathVariable Integer payrollId){
        return new ResponseEntity(payrollService.getPAYSlipContent(payrollId),HttpStatus.OK);
    }

    @GetMapping("/exportPDF/{payrollId}")
    public ResponseEntity<HttpStatusEntity> exportToPDF(@PathVariable Integer payrollId, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        PaySlipDTO paySlipDTO = payrollService.getPAYSlipContent(payrollId);

        PDFExporter exporter = new PDFExporter(paySlipDTO);
        exporter.export(response);
        return ResponseEntity.ok(new HttpStatusEntity(paySlipDTO,HttpStatus.OK.value(), "Pdf exported"));
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

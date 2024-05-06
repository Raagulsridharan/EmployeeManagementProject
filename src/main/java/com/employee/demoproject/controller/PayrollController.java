package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.entity.Payroll;
import com.employee.demoproject.service.PDFExporter;
import com.employee.demoproject.service.PayrollService;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.PAYROLL)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    /**
     * exporting to pdf format with the salary details
     * @param payrollId
     * @param response
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    @GetMapping("/exportPDF/{payrollId}")
    public ResponseEntity<HttpStatusResponse> exportToPDF(@PathVariable Integer payrollId, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        PaySlipDTO paySlipDTO = payrollService.getPAYSlipContent(payrollId);
        if(paySlipDTO!=null){
            PDFExporter exporter = new PDFExporter(paySlipDTO);
            exporter.export(response);
            return new ResponseEntity<>(new HttpStatusResponse(paySlipDTO,HttpStatus.OK.value(), "Pdf exported"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NO_CONTENT.value(), "Pdf exported"),HttpStatus.NO_CONTENT);
        }
    }

    /**
     * getting total payroll count
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/count")
    public ResponseEntity<HttpStatusResponse> totalPayrollCount() throws BusinessServiceException {
        Long count = payrollService.totalPayrollCount();
        if(count!=null){
            return new ResponseEntity<>(new HttpStatusResponse(count,HttpStatus.OK.value(), "Getting count"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "Not found"),HttpStatus.NO_CONTENT);
        }
    }

    /**
     * getting all payroll using filter option
     * @param filterOption
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping("/all")
    public ResponseEntity<HttpStatusResponse> filterPayroll(@RequestBody @Valid FilterOption filterOption) throws BusinessServiceException {
        return ofNullable(payrollService.filterPayroll(filterOption)).filter(CollectionUtils::isNotEmpty)
                .map(paySlipDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(paySlipDTOS, HttpStatus.OK.value(), "Payroll filtered successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "no data to filter"), HttpStatus.NO_CONTENT));
    }

    /**
     * fetching employee salary details with the help of empId
     * @param empId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("detail/{empId}")
    public ResponseEntity<HttpStatusResponse> employeeSalaryDetails(@PathVariable Integer empId) throws BusinessServiceException{
        EmployeePaymentDTO employeePaymentDTO = payrollService.getEmployeeSalaryDetails(empId);
        return new ResponseEntity<>(new HttpStatusResponse(employeePaymentDTO,HttpStatus.OK.value(),"Employee salary details Fetched"),HttpStatus.OK);
    }

    /**
     * getting employee payroll using empId
     * @param empId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeePayroll(@PathVariable int empId) throws BusinessServiceException {
        return new ResponseEntity<>(new HttpStatusResponse(payrollService.getEmployeePayroll(empId), HttpStatus.OK.value(),"Payroll for employee"),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmployeePayroll() throws BusinessServiceException{
        return new ResponseEntity<>(new HttpStatusResponse(payrollService.getAllEmployeePayroll(), HttpStatus.OK.value(),"Payroll of All the employees"),HttpStatus.OK);
    }

    @PostMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> makePayment(@PathVariable int empId, @RequestBody PayrollDTO payrollDTO) throws BusinessServiceException{
        return new ResponseEntity<>(new HttpStatusResponse(payrollService.makePayment(empId,payrollDTO),HttpStatus.OK.value(), "Successfully payment done"),HttpStatus.OK);
    }

    @PostMapping("/create/{empId}")
    public ResponseEntity<HttpStatusResponse> createPayroll(@PathVariable int empId) throws BusinessServiceException{
        PayrollDTO payrollDTO = payrollService.createPayroll(empId);
        if(payrollDTO!=null){
            return new ResponseEntity<>(new HttpStatusResponse(payrollDTO,HttpStatus.OK.value(),"Payroll created...!"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NO_CONTENT.value(),"Payroll not created...!"),HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> updatePayroll(@PathVariable int empId, @RequestBody PayrollDTO payrollDTO) throws BusinessServiceException{
        return new ResponseEntity<>(new HttpStatusResponse(payrollService.updatePayroll(empId,payrollDTO),HttpStatus.OK.value(),"Payroll updated...!"),HttpStatus.OK);
    }

    @GetMapping("/content/{payrollId}")
    public ResponseEntity<PayrollDTO> getPaySlipContent(@PathVariable Integer payrollId) throws BusinessServiceException{
        return new ResponseEntity(payrollService.getPAYSlipContent(payrollId),HttpStatus.OK);
    }



    //------------------------------------------------------

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

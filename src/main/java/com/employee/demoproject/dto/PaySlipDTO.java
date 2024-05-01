package com.employee.demoproject.dto;

import com.itextpdf.text.Image;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PaySlipDTO {
    private Integer empId;
    private String empName;
    private String deptName;
    private String designation;

    @PositiveOrZero(message = "Annual salary pack must be positive or zero")
    private Double annualSalaryPack;

    @PositiveOrZero(message = "Basic salary must be positive or zero")
    private Double basicSalary;

    @PositiveOrZero(message = "Tax reduction must be positive or zero")
    private Double taxReduction;

    @PositiveOrZero(message = "Net salary must be positive or zero")
    private Double netSalary;

    private Integer salaryId;
    private String month;

    public PaySlipDTO(){
    }

    public PaySlipDTO(Integer empId, String empName, String deptName, String designation, Double annualSalaryPack, Double basicSalary, Double taxReduction, Double netSalary, Integer salaryId, String month) {
        this.empId = empId;
        this.empName = empName;
        this.deptName = deptName;
        this.designation = designation;
        this.annualSalaryPack = annualSalaryPack;
        this.basicSalary = basicSalary;
        this.taxReduction = taxReduction;
        this.netSalary = netSalary;
        this.salaryId = salaryId;
        this.month = month;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getAnnualSalaryPack() {
        return annualSalaryPack;
    }

    public void setAnnualSalaryPack(Double annualSalaryPack) {
        this.annualSalaryPack = annualSalaryPack;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getTaxReduction() {
        return taxReduction;
    }

    public void setTaxReduction(Double taxReduction) {
        this.taxReduction = taxReduction;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

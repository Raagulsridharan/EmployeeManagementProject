package com.employee.demoproject.dto;

public class EmployeePaymentDTO {
    private int id;
    private String name;
    private String dept;
    private String role;
    private Double basic_sal_month;
    private Double tax_reduction_month;
    private Double net_sal_month;

    public EmployeePaymentDTO() {
    }

    public EmployeePaymentDTO(int id, String name, String dept, String role, Double basic_sal_month, Double tax_reduction_month, Double net_sal_month) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.role = role;
        this.basic_sal_month = basic_sal_month;
        this.tax_reduction_month = tax_reduction_month;
        this.net_sal_month = net_sal_month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getBasic_sal_month() {
        return basic_sal_month;
    }

    public void setBasic_sal_month(Double basic_sal_month) {
        this.basic_sal_month = basic_sal_month;
    }

    public Double getTax_reduction_month() {
        return tax_reduction_month;
    }

    public void setTax_reduction_month(Double tax_reduction_month) {
        this.tax_reduction_month = tax_reduction_month;
    }

    public Double getNet_sal_month() {
        return net_sal_month;
    }

    public void setNet_sal_month(Double net_sal_month) {
        this.net_sal_month = net_sal_month;
    }

    @Override
    public String toString() {
        return "EmployeePaymentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", role='" + role + '\'' +
                ", basic_sal_month=" + basic_sal_month +
                ", tax_reduction_month=" + tax_reduction_month +
                ", net_sal_month=" + net_sal_month +
                '}';
    }
}

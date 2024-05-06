package com.employee.demoproject.dto;

import jakarta.validation.constraints.NotNull;

public class DepartmentDTO {
    private Integer id;
    @NotNull
    private String name;
    private Long totalCount;
    public DepartmentDTO(){}
    public DepartmentDTO(Integer id, String name, Long totalCount){
        this.id = id;
        this.name = name;
        this.totalCount = totalCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}

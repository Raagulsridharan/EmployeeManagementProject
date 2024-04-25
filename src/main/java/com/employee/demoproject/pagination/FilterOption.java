package com.employee.demoproject.pagination;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FilterOption {

    @NotNull
    @Min(value = 1)
    @Max(value = 10, message = "Page number must be Greater than or equal to 1")
    private Integer pageNo;

    @NotNull
    @Min(value = 5)
    @Max(value = 20, message = "Page size must be greater than 5 and lesser than 20")
    private Integer pageSize;

    private String searchKey;

    public FilterOption(){}
    public FilterOption(Integer pageNo, Integer pageSize, String searchKey){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.searchKey = searchKey;
    }
    public FilterOption(Integer pageNo, Integer pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}

package com.employee.demoproject.responce;

public class HttpStatusResponse {
    private Object data;
    private Integer statusCode;
    private String description;

    public HttpStatusResponse() {
    }
    public HttpStatusResponse(Object data, Integer statusCode, String description){
        this.data = data;
        this.statusCode = statusCode;
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

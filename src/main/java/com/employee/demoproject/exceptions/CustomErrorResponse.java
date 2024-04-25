package com.employee.demoproject.exceptions;

import org.springframework.http.HttpStatus;

public class CustomErrorResponse {
    private String message;
    private HttpStatus status;

    public CustomErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}


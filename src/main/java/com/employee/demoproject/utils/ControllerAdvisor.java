package com.employee.demoproject.utils;

import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.CustomErrorResponse;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        JSONObject jsonObject = new JSONObject();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            jsonObject.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new HttpStatusResponse(null, status.value(),parseError(jsonObject).toString()), HttpStatus.BAD_REQUEST);
    }

    private Object parseError(JSONObject jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        Object error = new Object();
        try {
            error = mapper.readValue(jsonObject.toString(), Object.class);
        } catch (IOException e) {
            return "An error occurred while parsing the error response.";
        }
        return error;
    }

    @ExceptionHandler(value = BusinessServiceException.class)
    public ResponseEntity<CustomErrorResponse> handleMyCustomException(BusinessServiceException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
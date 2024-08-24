package com.example.ecommerce.controller;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.models.ErrorDetail;
import com.example.ecommerce.models.ErrorResp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResp> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<ErrorDetail> errors = ex.getConstraintViolations()
                .stream()
                .map(cv -> new ErrorDetail(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());

        ErrorResp errorResp = new ErrorResp();
        errorResp.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setDescriptionCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResp.setErrors(errors);
        errorResp.setPath(request.getDescription(false));

        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    // 404: Not found -> Indicates that the browser was able to communicate with a given server, but the server could not find what was requested.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ErrorResp handleEntityNotFound(HttpServletRequest request, ResourceNotFoundException ex) {
        return new ErrorResp(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), null, request.getRequestURI());
    }
}

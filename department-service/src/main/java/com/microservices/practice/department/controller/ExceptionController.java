package com.microservices.practice.department.controller;

import com.microservices.practice.department.vo.DepartmentNotFoundException;
import com.microservices.practice.department.vo.MicroserviceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {

    /*This is global exception handling for microservice where we can define status code and generic error response structure
            to be returned based on exception received.
            As because in an organization having a common structure for response is very important*/

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        ex.printStackTrace();
        MicroserviceException exception = new MicroserviceException(new Date(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())
                ,ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public final ResponseEntity<Object> handleDepartmentNotFoundException(DepartmentNotFoundException ex, WebRequest webRequest){

        MicroserviceException exception = new MicroserviceException(new Date(),String.valueOf(HttpStatus.NOT_FOUND.value())
                ,ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    /*We override handleMethodArgumentNotValid method of ResponseEntityExceptionHandler to handle user input validations
    * where we can customize our error responses*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        MicroserviceException exception = new MicroserviceException(new Date(),String.valueOf(HttpStatus.BAD_REQUEST.value())
                ,"Validation Failed",ex.getBindingResult().toString());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}

package com.my.creditadvisoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AssignationException extends Exception{

    public AssignationException(String message, Exception cause) {
        super(message, cause);
    }
    public AssignationException(String message) {
        super(message);
    }
}

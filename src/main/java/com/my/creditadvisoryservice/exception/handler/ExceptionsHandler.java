package com.my.creditadvisoryservice.exception.handler;

import com.my.creditadvisoryservice.exception.AssignationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AssignationException.class)
    private ResponseEntity<String> handleAssignationException(AssignationException assignationException) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(String.format("Assignation wasn't performed due to \n Cause: %s", assignationException.getMessage()));
    }
}

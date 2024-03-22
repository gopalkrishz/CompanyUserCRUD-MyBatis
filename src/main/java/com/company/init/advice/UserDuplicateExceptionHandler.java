package com.company.init.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class UserDuplicateExceptionHandler {
    @ExceptionHandler(value = {SqlDuplicationException.class})
    public ResponseEntity<Object> userNotFoundHandler(SqlDuplicationException exception){
        UserException userException = new UserException(
                exception.getMessage(),
                exception.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(userException,HttpStatus.NOT_FOUND);
    }
}

package com.company.init.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException exception){
        UserException userException = new UserException(
                exception.getMessage(),
                exception.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(userException,HttpStatus.NOT_FOUND);
    }
}

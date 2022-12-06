package com.example.demo.exception.exceptionAdvice;

import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.entity.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class UserAlreadyExistsExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Message> bookNotFoundException(UserAlreadyExistsException exception, WebRequest request) {
        Message message = new Message(HttpStatus.ALREADY_REPORTED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(message);
    }

}

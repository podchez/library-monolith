package com.podchez.librarymonolith.exception.handling;

import com.podchez.librarymonolith.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            AccountNotFoundException.class,
            AuthorNotFoundException.class,
            BookNotFoundException.class,
            RoleNotFoundException.class}
    )
    public ResponseEntity<ExceptionDetails> handleNotFoundException(Exception e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new ExceptionDetails(httpStatus, e.getMessage()),
                httpStatus);
    }

    @ExceptionHandler(value = {
            AccountAlreadyExistsException.class,
            AuthorAlreadyExistsException.class,
            RoleAlreadyExistsException.class}
    )
    public ResponseEntity<ExceptionDetails> handleAlreadyExistsException(Exception e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ExceptionDetails(httpStatus, e.getMessage()),
                httpStatus);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> handleValidationException(ConstraintViolationException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ExceptionDetails(httpStatus, e.getMessage()),
                httpStatus);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ExceptionDetails> handleLoginException(BadCredentialsException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ExceptionDetails(httpStatus, e.getMessage()),
                httpStatus);
    }
}

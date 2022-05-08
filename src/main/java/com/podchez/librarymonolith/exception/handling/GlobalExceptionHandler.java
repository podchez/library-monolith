package com.podchez.librarymonolith.exception.handling;

import com.podchez.librarymonolith.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            AccountNotFoundException.class,
            AuthorNotFoundException.class,
            BookNotFoundException.class,
            GenreNotFoundException.class,
            RoleNotFoundException.class}
    )
    public ResponseEntity<ExceptionDetails> handleNotFoundException(Exception e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new ExceptionDetails(httpStatus, e.getMessage(), request),
                httpStatus);
    }

    @ExceptionHandler(value = {
            AccountAlreadyExistsException.class,
            AuthorAlreadyExistsException.class,
            GenreAlreadyExistsException.class,
            RoleAlreadyExistsException.class}
    )
    public ResponseEntity<ExceptionDetails> handleAlreadyExistsException(Exception e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ExceptionDetails(httpStatus, e.getMessage(), request),
                httpStatus);
    }
}

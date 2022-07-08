package com.podchez.librarymonolith.exception.handling;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class ExceptionDetails {
    private final ZonedDateTime timestamp;
    private final int statusCode;
    private final HttpStatus error;
    private final String message;

    public ExceptionDetails(HttpStatus httpStatus, String message) {
        this.timestamp = ZonedDateTime.now();
        this.statusCode = httpStatus.value();
        this.error = httpStatus;
        this.message = message;
    }
}

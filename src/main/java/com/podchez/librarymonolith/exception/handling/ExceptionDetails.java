package com.podchez.librarymonolith.exception.handling;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@Getter
public class ExceptionDetails {
    private final ZonedDateTime timestamp;
    private final int status;
    private final HttpStatus error;
    private final String message;
    private final String path;

    public ExceptionDetails(HttpStatus httpStatus, String message, WebRequest request) {
        this.timestamp = ZonedDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus;
        this.message = message;
        this.path = request.getDescription(false);
    }
}

package com.socialnetwork.socialnetwork.controllers.exceptions;

import com.socialnetwork.socialnetwork.services.exceptions.DatabaseException;
import com.socialnetwork.socialnetwork.services.exceptions.FieldBlankException;
import com.socialnetwork.socialnetwork.services.exceptions.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StandardError> invalidFormat(InvalidFormatException e, HttpServletRequest request) {
        String error = "Invalid format";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FieldBlankException.class)
    public ResponseEntity<StandardError> fieldBlank(FieldBlankException e, HttpServletRequest request) {
        String error = "Field is blank";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Operation in database not allowed";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}

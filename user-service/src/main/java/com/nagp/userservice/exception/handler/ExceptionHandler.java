package com.nagp.userservice.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

public class ExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("User Not Found", ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", ex.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }
    
}

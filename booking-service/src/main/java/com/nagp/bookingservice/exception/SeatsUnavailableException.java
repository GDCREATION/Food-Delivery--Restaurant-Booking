package com.nagp.bookingservice.exception;

public class SeatsUnavailableException extends RuntimeException {

    public SeatsUnavailableException(String message) {
        super(message);
    }
}


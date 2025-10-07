package com.company.nextlabs.policygen.exception;

public class NextLabsApiException extends Exception {

    public NextLabsApiException(String message) {
        super(message);
    }

    public NextLabsApiException(String message, Throwable cause) {
        super(message, cause);
    }
}

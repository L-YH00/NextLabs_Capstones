package com.company.nextlabs.policygen.exception;

public class PolicyGenerationException extends Exception {

    public PolicyGenerationException(String message) {
        super(message);
    }

    public PolicyGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}

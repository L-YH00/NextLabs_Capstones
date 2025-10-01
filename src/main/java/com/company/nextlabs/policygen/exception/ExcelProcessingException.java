package com.company.nextlabs.policygen.exception;

public class ExcelProcessingException extends Exception {

    public ExcelProcessingException(String message) {
        super(message);
    }

    public ExcelProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

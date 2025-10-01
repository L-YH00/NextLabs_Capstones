package com.company.nextlabs.policygen.nextlabs.dto;

public class ApiResponse<T> {
    private T value;
    private int statusCode;
    private String message;
    private boolean success;

    public ApiResponse() {
        // default constructor
    }

    public ApiResponse(T value) {
        this.value = value;
        this.success = true;
    }

    public ApiResponse(T value, int statusCode, String message, boolean success) {
        this.value = value;
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }

    // Getters and setters
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
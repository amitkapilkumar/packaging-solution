package com.packaging.solution.exception;

public class InvalidCaseException extends RuntimeException {
    private final String message;
    public InvalidCaseException(String message) {
        super(message);
        this.message = message;
    }
}

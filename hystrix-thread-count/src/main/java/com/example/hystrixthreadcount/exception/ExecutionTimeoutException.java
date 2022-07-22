package com.example.hystrixthreadcount.exception;

public class ExecutionTimeoutException extends RuntimeException{
    public ExecutionTimeoutException(String message) {
        super(message);
    }
}

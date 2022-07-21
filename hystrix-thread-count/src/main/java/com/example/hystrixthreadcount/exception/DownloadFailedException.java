package com.example.hystrixthreadcount.exception;

public class DownloadFailedException extends RuntimeException{
    public DownloadFailedException(String message) {
        super(message);
    }
}

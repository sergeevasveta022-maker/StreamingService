package com.example.streaming_service.exception;

public class ReportNotFoundException extends RuntimeException{

    public ReportNotFoundException(String message) {
        super(message);
    }
}

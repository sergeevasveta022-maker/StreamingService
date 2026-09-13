package com.example.streaming_service.exception;

public class KinopoiskUnavailableException extends RuntimeException{

    public KinopoiskUnavailableException(String message){
        super(message);
    }

    public KinopoiskUnavailableException(String message, Throwable cause){
        super(message, cause);
    }
}

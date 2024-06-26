package com.socialnetwork.socialnetwork.services.exceptions;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException(String message) {
        super(message);
    }

    public InvalidTypeException(String data, Object value) {
        super("Type is not valid. " + data +  " " +  value);
    }
}

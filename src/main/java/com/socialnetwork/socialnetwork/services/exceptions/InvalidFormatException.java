package com.socialnetwork.socialnetwork.services.exceptions;

public class InvalidFormatException extends RuntimeException {

    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String data, Object value) {
        super("Format not is valid. " + data + " " + value);
    }
}

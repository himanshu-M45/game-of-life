package org.example.Exceptions;

public class InvalidInitializationValueException extends RuntimeException {
    public InvalidInitializationValueException(String message) {
        super(message);
    }
}

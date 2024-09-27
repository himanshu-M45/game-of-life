package org.example.Exceptions;

public class InvalidRowColumnValueException extends RuntimeException {
    public InvalidRowColumnValueException(String message) {
        super(message);
    }
}

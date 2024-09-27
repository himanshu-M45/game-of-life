package org.example.Exceptions;

public class InvalidPercentageException extends RuntimeException {
    public InvalidPercentageException(String message) {
        super(message);
    }
}

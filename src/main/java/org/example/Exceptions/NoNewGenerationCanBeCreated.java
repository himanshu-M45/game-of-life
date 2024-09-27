package org.example.Exceptions;

public class NoNewGenerationCanBeCreated extends RuntimeException {
    public NoNewGenerationCanBeCreated(String message) {
        super(message);
    }
}

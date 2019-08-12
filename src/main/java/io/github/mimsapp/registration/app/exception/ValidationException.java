package io.github.mimsapp.registration.app.exception;

public class ValidationException extends Exception {

    private String message;

    public ValidationException(String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }
}

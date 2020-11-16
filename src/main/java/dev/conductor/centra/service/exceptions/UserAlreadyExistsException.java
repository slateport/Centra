package dev.conductor.centra.service.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String reason) {
        super(reason);
    }
}

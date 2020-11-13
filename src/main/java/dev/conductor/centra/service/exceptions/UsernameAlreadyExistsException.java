package dev.conductor.centra.service.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String reason) {
        super(reason);
    }
}

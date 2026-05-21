package com.example.gerenciador.hotel.domain.exception;

public abstract class ValidationException extends DomainException {

    public ValidationException(String message) {
        super(message);
    }
}

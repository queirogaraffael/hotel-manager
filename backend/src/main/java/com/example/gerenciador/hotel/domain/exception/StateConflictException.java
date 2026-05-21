package com.example.gerenciador.hotel.domain.exception;

public abstract class StateConflictException extends DomainException {

    public StateConflictException(String message) {
        super(message);
    }
}

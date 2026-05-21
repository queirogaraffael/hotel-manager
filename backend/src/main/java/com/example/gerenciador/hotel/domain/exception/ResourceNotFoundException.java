package com.example.gerenciador.hotel.domain.exception;

public abstract class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

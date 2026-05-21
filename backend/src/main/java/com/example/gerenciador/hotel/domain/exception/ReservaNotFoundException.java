package com.example.gerenciador.hotel.domain.exception;

public class ReservaNotFoundException extends ResourceNotFoundException {

    public ReservaNotFoundException(String message) {
        super(message);
    }
}

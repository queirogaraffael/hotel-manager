package com.example.gerenciador.hotel.domain.exception;

public class QuartoNotFoundException extends ResourceNotFoundException {

    public QuartoNotFoundException(String message) {
        super(message);
    }
}

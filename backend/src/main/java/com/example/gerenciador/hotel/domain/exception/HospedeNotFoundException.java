package com.example.gerenciador.hotel.domain.exception;

public class HospedeNotFoundException extends ResourceNotFoundException {

    public HospedeNotFoundException(String message) {
        super(message);
    }
}

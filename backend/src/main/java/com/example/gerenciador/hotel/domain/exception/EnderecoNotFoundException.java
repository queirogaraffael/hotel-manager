package com.example.gerenciador.hotel.domain.exception;

public class EnderecoNotFoundException extends ResourceNotFoundException {

    public EnderecoNotFoundException(String message) {
        super(message);
    }
}

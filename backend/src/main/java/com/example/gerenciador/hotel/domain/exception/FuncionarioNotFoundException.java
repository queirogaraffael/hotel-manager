package com.example.gerenciador.hotel.domain.exception;

public class FuncionarioNotFoundException extends ResourceNotFoundException {

    public FuncionarioNotFoundException(String message) {
        super(message);
    }
}

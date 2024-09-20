package com.example.gerenciador_hotel_spring.exceptions;

public class EnderecoJaExisteException extends RuntimeException {

    public EnderecoJaExisteException(String message) {
        super(message);
    }
}

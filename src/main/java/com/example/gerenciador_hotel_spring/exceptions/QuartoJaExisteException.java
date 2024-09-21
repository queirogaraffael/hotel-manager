package com.example.gerenciador_hotel_spring.exceptions;

public class QuartoJaExisteException extends RuntimeException{

    public QuartoJaExisteException(String message) {
        super(message);
    }
}

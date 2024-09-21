package com.example.gerenciador_hotel_spring.exceptions;

public class HospedeJaExisteException extends RuntimeException{

    public HospedeJaExisteException(String message) {
        super(message);
    }
}

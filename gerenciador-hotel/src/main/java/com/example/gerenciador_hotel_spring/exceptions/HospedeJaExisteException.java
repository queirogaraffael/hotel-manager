package com.example.gerenciador_hotel_spring.exceptions;

import java.io.Serial;

public class HospedeJaExisteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public HospedeJaExisteException(String message) {
        super(message);
    }
}

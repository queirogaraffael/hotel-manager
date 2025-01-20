package com.example.gerenciador_hotel_spring.exceptions;

import java.io.Serial;

public class QuartoJaExisteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public QuartoJaExisteException(String message) {
        super(message);
    }
}

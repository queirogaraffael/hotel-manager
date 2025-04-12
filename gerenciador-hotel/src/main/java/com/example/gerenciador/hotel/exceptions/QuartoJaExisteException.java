package com.example.gerenciador.hotel.exceptions;

import java.io.Serial;

public class QuartoJaExisteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public QuartoJaExisteException(String message) {
        super(message);
    }
}

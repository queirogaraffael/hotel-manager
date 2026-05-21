package com.example.gerenciador.hotel.shared.exception;

import java.io.Serial;

public class QuartoJaExisteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public QuartoJaExisteException(String message) {
        super(message);
    }
}

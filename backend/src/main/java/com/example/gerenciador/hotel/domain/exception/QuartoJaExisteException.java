package com.example.gerenciador.hotel.domain.exception;

import java.io.Serial;

public class QuartoJaExisteException extends StateConflictException{

    @Serial
    private static final long serialVersionUID = 1L;

    public QuartoJaExisteException(String message) {
        super(message);
    }
}

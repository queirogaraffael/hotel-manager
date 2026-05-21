package com.example.gerenciador.hotel.domain.exception;

import java.io.Serial;

public class HospedeJaExisteException extends StateConflictException{

    @Serial
    private static final long serialVersionUID = 1L;

    public HospedeJaExisteException(String message) {
        super(message);
    }
}

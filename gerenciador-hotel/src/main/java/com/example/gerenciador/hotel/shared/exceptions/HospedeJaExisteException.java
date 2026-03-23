package com.example.gerenciador.hotel.shared.exceptions;

import java.io.Serial;

public class HospedeJaExisteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public HospedeJaExisteException(String message) {
        super(message);
    }
}

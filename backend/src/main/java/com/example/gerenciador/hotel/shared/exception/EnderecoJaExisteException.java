package com.example.gerenciador.hotel.shared.exception;

import java.io.Serial;

public class EnderecoJaExisteException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EnderecoJaExisteException(String message) {
        super(message);
    }
}

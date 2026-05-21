package com.example.gerenciador.hotel.domain.exception;

import java.io.Serial;

public class EnderecoJaExisteException extends StateConflictException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EnderecoJaExisteException(String message) {
        super(message);
    }
}

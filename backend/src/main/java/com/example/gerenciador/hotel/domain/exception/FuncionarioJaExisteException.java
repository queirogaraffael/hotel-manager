package com.example.gerenciador.hotel.domain.exception;

import java.io.Serial;

public class FuncionarioJaExisteException extends StateConflictException{

    @Serial
    private static final long serialVersionUID = 1L;

    public FuncionarioJaExisteException(String message) {
        super(message);
    }
}

package com.example.gerenciador.hotel.shared.exception;

import java.io.Serial;

public class FuncionarioJaExisteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public FuncionarioJaExisteException(String message) {
        super(message);
    }
}

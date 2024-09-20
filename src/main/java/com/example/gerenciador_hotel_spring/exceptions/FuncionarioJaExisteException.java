package com.example.gerenciador_hotel_spring.exceptions;

import java.io.Serial;

public class FuncionarioJaExisteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public FuncionarioJaExisteException(String message) {
        super(message);
    }
}

package com.example.gerenciador_hotel_spring.exceptions;

import java.io.Serial;

public class ExtratoJaExisteParaMesReferenteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ExtratoJaExisteParaMesReferenteException(String message) {
        super(message);
    }
}

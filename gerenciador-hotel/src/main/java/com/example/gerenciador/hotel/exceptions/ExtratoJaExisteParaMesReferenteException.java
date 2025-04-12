package com.example.gerenciador.hotel.exceptions;

import java.io.Serial;

public class ExtratoJaExisteParaMesReferenteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ExtratoJaExisteParaMesReferenteException(String message) {
        super(message);
    }
}

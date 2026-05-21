package com.example.gerenciador.hotel.domain.exception;

import java.io.Serial;

public class ExtratoJaExisteParaMesReferenteException extends StateConflictException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ExtratoJaExisteParaMesReferenteException(String message) {
        super(message);
    }
}

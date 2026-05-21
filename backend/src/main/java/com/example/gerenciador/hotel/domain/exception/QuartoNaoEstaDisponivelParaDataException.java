package com.example.gerenciador.hotel.domain.exception;

import java.io.Serial;

public class QuartoNaoEstaDisponivelParaDataException extends StateConflictException{

    @Serial
    private static final long serialVersionUID = 1L;

    public QuartoNaoEstaDisponivelParaDataException(String message) {
        super(message);
    }
}

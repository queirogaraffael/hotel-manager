package com.example.gerenciador_hotel_spring.exceptions;

import java.io.Serial;

public class QuartoNaoEstaDisponivelParaDataException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public QuartoNaoEstaDisponivelParaDataException(String message) {
        super(message);
    }
}

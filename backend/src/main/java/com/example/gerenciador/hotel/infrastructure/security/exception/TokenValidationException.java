package com.example.gerenciador.hotel.infrastructure.security.exception;

public class TokenValidationException extends RuntimeException {

    public TokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

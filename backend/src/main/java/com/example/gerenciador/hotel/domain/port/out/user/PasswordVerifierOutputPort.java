package com.example.gerenciador.hotel.domain.port.out.user;

public interface PasswordVerifierOutputPort {
    boolean matches(String rawPassword, String encodedPassword);
}

package com.example.gerenciador.hotel.infrastructure.security;

import com.example.gerenciador.hotel.domain.port.out.user.PasswordVerifierOutputPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordVerifierAdapter implements PasswordVerifierOutputPort {

    private final PasswordEncoder passwordEncoder;

    public BcryptPasswordVerifierAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

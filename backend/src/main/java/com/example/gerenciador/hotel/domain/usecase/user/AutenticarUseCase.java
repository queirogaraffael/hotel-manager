package com.example.gerenciador.hotel.domain.usecase.user;

import com.example.gerenciador.hotel.domain.exception.InvalidCredentialsException;
import com.example.gerenciador.hotel.domain.exception.UserNotFoundException;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.AutenticarInputPort;
import com.example.gerenciador.hotel.domain.port.out.user.FindUserByUsernameOutputPort;
import com.example.gerenciador.hotel.domain.port.out.user.PasswordVerifierOutputPort;
import org.springframework.transaction.annotation.Transactional;

public class AutenticarUseCase implements AutenticarInputPort {

    private final FindUserByUsernameOutputPort findUserByUsernameOutputPort;
    private final PasswordVerifierOutputPort passwordVerifier;

    public AutenticarUseCase(
            FindUserByUsernameOutputPort findUserByUsernameOutputPort,
            PasswordVerifierOutputPort passwordVerifier) {
        this.findUserByUsernameOutputPort = findUserByUsernameOutputPort;
        this.passwordVerifier = passwordVerifier;
    }

    @Override
    @Transactional(readOnly = true)
    public User autenticar(String username, String password) {
        User user = findUserByUsernameOutputPort.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário '" + username + "' não encontrado."));

        if (!passwordVerifier.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Credenciais inválidas.");
        }

        return user;
    }
}

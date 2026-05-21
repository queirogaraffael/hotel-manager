package com.example.gerenciador.hotel.domain.usecase.user;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.AutenticarInputPort;
import com.example.gerenciador.hotel.domain.port.out.user.FindUserByUsernameOutputPort;
import com.example.gerenciador.hotel.domain.exception.UserNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AutenticarUseCase implements AutenticarInputPort {

    private final FindUserByUsernameOutputPort findUserByUsernameOutputPort;

    public AutenticarUseCase(FindUserByUsernameOutputPort findUserByUsernameOutputPort) {
        this.findUserByUsernameOutputPort = findUserByUsernameOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public User autenticar(String username, String password) {
        return findUserByUsernameOutputPort.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário '" + username + "' não encontrado."));
    }
}

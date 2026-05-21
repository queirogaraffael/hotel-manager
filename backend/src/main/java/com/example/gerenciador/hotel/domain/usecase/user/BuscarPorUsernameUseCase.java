package com.example.gerenciador.hotel.domain.usecase.user;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.BuscarPorUsernameInputPort;
import com.example.gerenciador.hotel.domain.port.out.user.FindUserByUsernameOutputPort;
import com.example.gerenciador.hotel.domain.exception.UserNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BuscarPorUsernameUseCase implements BuscarPorUsernameInputPort {

    private final FindUserByUsernameOutputPort findUserByUsernameOutputPort;

    public BuscarPorUsernameUseCase(FindUserByUsernameOutputPort findUserByUsernameOutputPort) {
        this.findUserByUsernameOutputPort = findUserByUsernameOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public User buscarPorUsername(String username) {
        return findUserByUsernameOutputPort.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário '" + username + "' não encontrado."));
    }
}

package com.example.gerenciador.hotel.domain.usecase.user;

import com.example.gerenciador.hotel.domain.exception.UserNotFoundException;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.BuscarUserPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.out.user.FindUserByIdOutputPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class BuscarUserPorIdUseCase implements BuscarUserPorIdInputPort {

    private final FindUserByIdOutputPort findUserByIdOutputPort;

    public BuscarUserPorIdUseCase(FindUserByIdOutputPort findUserByIdOutputPort) {
        this.findUserByIdOutputPort = findUserByIdOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public User buscarPorId(UUID id) {
        return findUserByIdOutputPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com id '" + id + "' não encontrado."));
    }
}

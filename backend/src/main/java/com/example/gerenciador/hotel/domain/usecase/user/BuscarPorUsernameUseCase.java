package com.example.gerenciador.hotel.domain.usecase.user;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.BuscarPorUsernameInputPort;
import com.example.gerenciador.hotel.domain.port.out.user.FindUserByUsernameOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarPorUsernameUseCase implements BuscarPorUsernameInputPort {

    private final FindUserByUsernameOutputPort findUserByUsernameOutputPort;

    @Override
    @Transactional(readOnly = true)
    public User buscarPorUsername(String username) {
        return findUserByUsernameOutputPort.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário '" + username + "' não encontrado."));
    }
}

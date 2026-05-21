package com.example.gerenciador.hotel.domain.port.in.user;

import com.example.gerenciador.hotel.domain.model.User;

public interface BuscarPorUsernameUseCase {
    User buscarPorUsername(String username);
}

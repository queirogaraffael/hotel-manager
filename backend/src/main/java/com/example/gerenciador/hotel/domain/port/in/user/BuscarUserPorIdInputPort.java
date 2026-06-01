package com.example.gerenciador.hotel.domain.port.in.user;

import com.example.gerenciador.hotel.domain.model.User;

import java.util.UUID;

public interface BuscarUserPorIdInputPort {
    User buscarPorId(UUID id);
}

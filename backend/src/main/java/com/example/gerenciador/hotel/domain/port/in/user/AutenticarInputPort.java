package com.example.gerenciador.hotel.domain.port.in.user;

import com.example.gerenciador.hotel.domain.model.User;

public interface AutenticarInputPort {
    User autenticar(String username, String password);
}

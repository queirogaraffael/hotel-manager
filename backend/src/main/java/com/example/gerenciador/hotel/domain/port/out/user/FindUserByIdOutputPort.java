package com.example.gerenciador.hotel.domain.port.out.user;

import com.example.gerenciador.hotel.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface FindUserByIdOutputPort {
    Optional<User> findById(UUID id);
}

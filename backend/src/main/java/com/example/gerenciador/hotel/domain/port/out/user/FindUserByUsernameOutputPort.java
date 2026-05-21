package com.example.gerenciador.hotel.domain.port.out.user;

import com.example.gerenciador.hotel.domain.model.User;
import java.util.Optional;

public interface FindUserByUsernameOutputPort {
    Optional<User> findByUsername(String username);
}

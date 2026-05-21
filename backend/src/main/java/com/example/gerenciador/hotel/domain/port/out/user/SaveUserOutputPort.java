package com.example.gerenciador.hotel.domain.port.out.user;

import com.example.gerenciador.hotel.domain.model.User;

public interface SaveUserOutputPort {
    User save(User user);
}

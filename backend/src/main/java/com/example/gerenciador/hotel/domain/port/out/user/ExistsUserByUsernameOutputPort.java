package com.example.gerenciador.hotel.domain.port.out.user;

public interface ExistsUserByUsernameOutputPort {
    boolean existsByUsername(String username);
}

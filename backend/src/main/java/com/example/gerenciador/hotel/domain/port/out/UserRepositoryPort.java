package com.example.gerenciador.hotel.domain.port.out;

import com.example.gerenciador.hotel.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByCpf(String cpf);
}

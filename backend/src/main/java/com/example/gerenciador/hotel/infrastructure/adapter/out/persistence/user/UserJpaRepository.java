package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByCpf(String cpf);
}

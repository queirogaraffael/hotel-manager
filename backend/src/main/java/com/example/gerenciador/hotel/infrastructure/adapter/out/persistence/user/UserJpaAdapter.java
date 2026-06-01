package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.user;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.out.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements
        SaveUserOutputPort,
        FindUserByIdOutputPort,
        FindUserByUsernameOutputPort,
        ExistsUserByUsernameOutputPort,
        ExistsUserByCpfOutputPort {

    private final UserJpaRepository jpaRepository;
    private final UserEntityMapper mapper;

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(jpaRepository.findByUsername(username))
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }
}

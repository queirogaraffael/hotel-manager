package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.user;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.endereco.EnderecoEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnderecoEntityMapper.class})
public interface UserEntityMapper {

    // Ignora relações bidirecionais (hospede/funcionario) para evitar ciclo
    @Mapping(target = "hospede", ignore = true)
    @Mapping(target = "funcionario", ignore = true)
    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);
}

package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.endereco;

import com.example.gerenciador.hotel.domain.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoEntityMapper {

    // Ignora a relação bidirecional com User para evitar ciclo infinito
    @Mapping(target = "user", ignore = true)
    EnderecoEntity toEntity(Endereco domain);

    Endereco toDomain(EnderecoEntity entity);
}

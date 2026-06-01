package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExtratoFuncionarioEntityMapper {

    @Mapping(target = "funcionario.id", source = "funcionarioId")
    ExtratoFuncionarioEntity toEntity(ExtratoFuncionario domain);

    @Mapping(target = "funcionarioId", source = "funcionario.id")
    ExtratoFuncionario toDomain(ExtratoFuncionarioEntity entity);
}

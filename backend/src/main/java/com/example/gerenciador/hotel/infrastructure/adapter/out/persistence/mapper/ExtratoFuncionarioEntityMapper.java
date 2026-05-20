package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.ExtratoFuncionarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FuncionarioEntityMapper.class})
public interface ExtratoFuncionarioEntityMapper {

    ExtratoFuncionarioEntity toEntity(ExtratoFuncionario domain);

    // Ignora o funcionario para evitar ciclo Extrato→Funcionario→Extrato
    @Mapping(target = "funcionario", ignore = true)
    ExtratoFuncionario toDomain(ExtratoFuncionarioEntity entity);
}

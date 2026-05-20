package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.FuncionarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserEntityMapper.class, ExtratoFuncionarioEntityMapper.class})
public interface FuncionarioEntityMapper {

    FuncionarioEntity toEntity(Funcionario domain);

    // Ignora a lista de extratos para evitar ciclo Funcionario→Extrato→Funcionario
    @Mapping(target = "extratoFuncionario", ignore = true)
    Funcionario toDomain(FuncionarioEntity entity);
}

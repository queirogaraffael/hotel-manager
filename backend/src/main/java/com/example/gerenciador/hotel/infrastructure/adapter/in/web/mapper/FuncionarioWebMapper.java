package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.funcionario.FuncionarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionarioWebMapper {

    @Mapping(target = "cpf", source = "user.cpf")
    @Mapping(target = "nome", source = "user.nome")
    FuncionarioResponseDTO toResponse(Funcionario domain);
}

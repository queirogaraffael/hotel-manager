package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.extratofuncionario.ExtratoFuncionarioResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtratoFuncionarioWebMapper {

    ExtratoFuncionarioResponseDTO toResponse(ExtratoFuncionario domain);
}

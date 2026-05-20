package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.hospede.HospedeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HospedeWebMapper {

    @Mapping(target = "cpf", source = "user.cpf")
    @Mapping(target = "nome", source = "user.nome")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "telefone", source = "user.telefone")
    HospedeResponseDTO toResponse(Hospede domain);
}

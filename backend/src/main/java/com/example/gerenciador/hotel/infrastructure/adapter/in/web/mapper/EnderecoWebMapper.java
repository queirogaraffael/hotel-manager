package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoWebMapper {

    Endereco toDomain(EnderecoRequestDTO dto);

    EnderecoResponseDTO toResponse(Endereco domain);
}

package com.example.gerenciador.hotel.shared.mappers;

import com.example.gerenciador.hotel.shared.dtos.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.entities.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    Endereco toEntity(EnderecoRequestDTO dto);
}

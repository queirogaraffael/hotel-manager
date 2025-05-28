package com.example.gerenciador.hotel.shared.mappers;

import com.example.gerenciador.hotel.shared.dtos.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.domain.entities.Endereco;

public interface EnderecoMapper {
    Endereco toEntity(EnderecoRequestDTO dto);
}

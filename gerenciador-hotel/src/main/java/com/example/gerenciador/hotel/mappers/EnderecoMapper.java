package com.example.gerenciador.hotel.mappers;

import com.example.gerenciador.hotel.dtos.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.entities.Endereco;

public interface EnderecoMapper {
    Endereco toEntity(EnderecoRequestDTO dto);
}

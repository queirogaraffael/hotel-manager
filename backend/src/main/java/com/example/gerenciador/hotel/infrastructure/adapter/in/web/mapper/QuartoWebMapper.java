package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.quarto.QuartoResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuartoWebMapper {

    QuartoResponseDTO toResponse(Quarto domain);
}

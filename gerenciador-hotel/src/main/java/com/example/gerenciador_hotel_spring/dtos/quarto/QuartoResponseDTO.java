package com.example.gerenciador_hotel_spring.dtos.quarto;

import com.example.gerenciador_hotel_spring.enums.TipoQuarto;

public record QuartoResponseDTO(Long id, String numero, TipoQuarto tipoQuarto) {
}

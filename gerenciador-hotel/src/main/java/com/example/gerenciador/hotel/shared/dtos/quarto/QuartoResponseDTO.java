package com.example.gerenciador.hotel.shared.dtos.quarto;


import com.example.gerenciador.hotel.domain.enums.TipoQuarto;

public record QuartoResponseDTO(Long id, String numero, TipoQuarto tipoQuarto) {
}

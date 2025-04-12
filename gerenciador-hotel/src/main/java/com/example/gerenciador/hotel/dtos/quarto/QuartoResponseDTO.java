package com.example.gerenciador.hotel.dtos.quarto;


import com.example.gerenciador.hotel.enums.TipoQuarto;

public record QuartoResponseDTO(Long id, String numero, TipoQuarto tipoQuarto) {
}

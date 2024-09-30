package com.example.gerenciador_hotel_spring.dtos.quarto;

import com.example.gerenciador_hotel_spring.enums.StatusQuarto;
import com.example.gerenciador_hotel_spring.enums.TipoQuarto;

public record QuartoCreateDTO(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria,
                              StatusQuarto statusQuarto) {
}


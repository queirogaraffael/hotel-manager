package com.example.gerenciador.hotel.shared.dtos.quarto;


import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;

public record QuartoUpdateDTO(TipoQuarto tipoQuarto, int capacidade, double precoDiaria,
                              StatusQuarto statusQuarto) {
}

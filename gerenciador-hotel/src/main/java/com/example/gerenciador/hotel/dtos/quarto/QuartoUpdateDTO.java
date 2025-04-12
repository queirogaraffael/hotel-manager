package com.example.gerenciador.hotel.dtos.quarto;


import com.example.gerenciador.hotel.enums.StatusQuarto;
import com.example.gerenciador.hotel.enums.TipoQuarto;

public record QuartoUpdateDTO(TipoQuarto tipoQuarto, int capacidade, double precoDiaria,
                              StatusQuarto statusQuarto) {
}

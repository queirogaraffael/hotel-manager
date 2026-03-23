package com.example.gerenciador.hotel.shared.dtos.quarto;


import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoResponseDTO {
    private Long id;
    private String numero;
    private TipoQuarto tipoQuarto;
    private int capacidade;
    private double precoDiaria;
    private StatusQuarto statusQuarto;
}
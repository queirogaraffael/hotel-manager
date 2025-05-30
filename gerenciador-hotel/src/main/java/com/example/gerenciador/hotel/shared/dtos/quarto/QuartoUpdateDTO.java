package com.example.gerenciador.hotel.shared.dtos.quarto;


import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoUpdateDTO {
    private String numero;
    private TipoQuarto tipoQuarto;
    private Integer capacidade;
    private Double precoDiaria;
    private StatusQuarto statusQuarto;
}
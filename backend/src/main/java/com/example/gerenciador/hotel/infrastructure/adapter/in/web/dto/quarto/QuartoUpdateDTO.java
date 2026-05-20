package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoUpdateDTO {
    private TipoQuarto tipoQuarto;
    private Integer capacidade;
    private Double precoDiaria;
    private StatusQuarto statusQuarto;
}

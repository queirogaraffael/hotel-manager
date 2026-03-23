package com.example.gerenciador.hotel.shared.dtos.quarto;


import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoRequestDTO {

    @NotBlank(message = "Número do quarto é obrigatório")
    private String numero;

    @NotNull(message = "Tipo do quarto é obrigatório")
    private TipoQuarto tipoQuarto;

    @Min(value = 1, message = "Capacidade mínima deve ser 1")
    private int capacidade;

    @Positive(message = "Preço da diária deve ser maior que zero")
    private double precoDiaria;

    @NotNull(message = "Status do quarto é obrigatório")
    private StatusQuarto statusQuarto;
}
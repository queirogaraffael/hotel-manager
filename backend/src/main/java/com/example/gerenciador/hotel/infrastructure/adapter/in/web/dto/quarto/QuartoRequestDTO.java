package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoRequestDTO {

    @NotBlank(message = "O número do quarto é obrigatório")
    private String numero;

    @NotNull(message = "O tipo de quarto é obrigatório")
    private TipoQuarto tipoQuarto;

    @Positive(message = "A capacidade deve ser maior que zero")
    private int capacidade;

    @Positive(message = "O preço de diária deve ser maior que zero")
    private double precoDiaria;

    @NotNull(message = "O status do quarto é obrigatório")
    private StatusQuarto statusQuarto;
}

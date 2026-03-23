package com.example.gerenciador.hotel.shared.dtos.extratofuncionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoFuncionarioRequestDTO {

    @NotNull(message = "A data do extrato não pode ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataExtrato;

    @PositiveOrZero(message = "Horas trabalhadas não podem ser negativas")
    private double horasTrabalhadas;

    @Positive(message = "Valor da hora deve ser maior que zero")
    private double valorHora;

    @PositiveOrZero(message = "Salário não pode ser negativo")
    private double salario;

    @NotNull(message = "O ID do funcionário não pode ser nulo")
    private Long funcionarioId;
}
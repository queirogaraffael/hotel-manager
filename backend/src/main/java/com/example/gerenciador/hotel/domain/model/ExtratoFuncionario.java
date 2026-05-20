package com.example.gerenciador.hotel.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExtratoFuncionario {

    private Long id;

    @EqualsAndHashCode.Include
    private Date dataExtrato;

    private double horasTrabalhadas;
    private double valorHora;
    private double salario;

    private Funcionario funcionario;
}

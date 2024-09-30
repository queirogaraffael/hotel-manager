package com.example.gerenciador_hotel_spring.dtos.extratoFuncionario;

import java.util.Date;

public record ExtratoFuncionarioDTO(Date dataExtrato, double horasTrabalhadas, double valorHora,  double salario) {
}

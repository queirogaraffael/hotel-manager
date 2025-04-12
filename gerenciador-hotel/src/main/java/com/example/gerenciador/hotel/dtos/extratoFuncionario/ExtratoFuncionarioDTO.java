package com.example.gerenciador.hotel.dtos.extratoFuncionario;

import java.util.Date;

public record ExtratoFuncionarioDTO(Date dataExtrato, double horasTrabalhadas, double valorHora,  double salario) {
}

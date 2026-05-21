package com.example.gerenciador.hotel.domain.port.in.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import java.util.Date;

public interface CriarExtratoInputPort {
    ExtratoFuncionario criarExtrato(String cpfFuncionario, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario);
}

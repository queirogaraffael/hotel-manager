package com.example.gerenciador.hotel.domain.port.in.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import java.util.Date;

public interface EditarExtratoInputPort {
    ExtratoFuncionario editarExtrato(Long id, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario);
}

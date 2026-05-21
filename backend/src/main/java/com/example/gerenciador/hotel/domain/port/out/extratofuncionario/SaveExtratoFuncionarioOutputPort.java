package com.example.gerenciador.hotel.domain.port.out.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;

public interface SaveExtratoFuncionarioOutputPort {
    ExtratoFuncionario save(ExtratoFuncionario extrato);
}

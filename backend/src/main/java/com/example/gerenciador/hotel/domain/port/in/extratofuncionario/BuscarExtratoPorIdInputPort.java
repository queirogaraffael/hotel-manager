package com.example.gerenciador.hotel.domain.port.in.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;

public interface BuscarExtratoPorIdInputPort {
    ExtratoFuncionario buscarExtratoPorId(Long id);
}

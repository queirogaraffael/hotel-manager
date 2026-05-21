package com.example.gerenciador.hotel.domain.port.in.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;

public interface BuscarExtratoPorIdUseCase {
    ExtratoFuncionario buscarExtratoPorId(Long id);
}

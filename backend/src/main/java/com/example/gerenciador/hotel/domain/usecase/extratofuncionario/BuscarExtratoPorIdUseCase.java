package com.example.gerenciador.hotel.domain.usecase.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.BuscarExtratoPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.FindExtratoFuncionarioByIdOutputPort;
import com.example.gerenciador.hotel.domain.exception.ExtratoNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BuscarExtratoPorIdUseCase implements BuscarExtratoPorIdInputPort {

    private final FindExtratoFuncionarioByIdOutputPort findExtratoFuncionarioByIdOutputPort;

    public BuscarExtratoPorIdUseCase(FindExtratoFuncionarioByIdOutputPort findExtratoFuncionarioByIdOutputPort) {
        this.findExtratoFuncionarioByIdOutputPort = findExtratoFuncionarioByIdOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public ExtratoFuncionario buscarExtratoPorId(Long id) {
        return findExtratoFuncionarioByIdOutputPort.findById(id)
                .orElseThrow(() -> new ExtratoNotFoundException("Extrato com id " + id + " não encontrado."));
    }
}

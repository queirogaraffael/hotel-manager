package com.example.gerenciador.hotel.domain.usecase.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.BuscarExtratoPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.FindExtratoFuncionarioByIdOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarExtratoPorIdUseCase implements BuscarExtratoPorIdInputPort {

    private final FindExtratoFuncionarioByIdOutputPort findExtratoFuncionarioByIdOutputPort;

    @Override
    @Transactional(readOnly = true)
    public ExtratoFuncionario buscarExtratoPorId(Long id) {
        return findExtratoFuncionarioByIdOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Extrato com id " + id + " não encontrado."));
    }
}

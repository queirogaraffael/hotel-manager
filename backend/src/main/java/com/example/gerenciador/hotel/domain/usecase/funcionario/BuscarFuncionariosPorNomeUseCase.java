package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionariosPorNomeInputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindFuncionarioByNomeOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class BuscarFuncionariosPorNomeUseCase implements BuscarFuncionariosPorNomeInputPort {

    private final FindFuncionarioByNomeOutputPort findFuncionarioByNomeOutputPort;

    public BuscarFuncionariosPorNomeUseCase(FindFuncionarioByNomeOutputPort findFuncionarioByNomeOutputPort) {
        this.findFuncionarioByNomeOutputPort = findFuncionarioByNomeOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Funcionario> buscarFuncionariosPorNome(String nome, Pageable pageable) {
        return findFuncionarioByNomeOutputPort.findByNome(nome, pageable);
    }
}

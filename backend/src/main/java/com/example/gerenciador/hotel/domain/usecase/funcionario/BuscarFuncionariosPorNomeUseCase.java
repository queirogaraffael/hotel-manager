package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionariosPorNomeInputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindFuncionarioByNomeOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarFuncionariosPorNomeUseCase implements BuscarFuncionariosPorNomeInputPort {

    private final FindFuncionarioByNomeOutputPort findFuncionarioByNomeOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Page<Funcionario> buscarFuncionariosPorNome(String nome, Pageable pageable) {
        return findFuncionarioByNomeOutputPort.findByNome(nome, pageable);
    }
}

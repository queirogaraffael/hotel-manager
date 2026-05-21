package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindFuncionarioByCpfOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarFuncionarioPorCpfUseCase implements BuscarFuncionarioPorCpfInputPort {

    private final FindFuncionarioByCpfOutputPort findFuncionarioByCpfOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        return findFuncionarioByCpfOutputPort.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário com CPF " + cpf + " não encontrado."));
    }
}

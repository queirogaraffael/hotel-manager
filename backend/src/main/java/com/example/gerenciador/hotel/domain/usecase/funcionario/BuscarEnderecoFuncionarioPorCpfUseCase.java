package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarEnderecoFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoFuncionarioByCpfOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BuscarEnderecoFuncionarioPorCpfUseCase implements BuscarEnderecoFuncionarioPorCpfInputPort {

    private final GetEnderecoFuncionarioByCpfOutputPort getEnderecoFuncionarioByCpfOutputPort;

    public BuscarEnderecoFuncionarioPorCpfUseCase(GetEnderecoFuncionarioByCpfOutputPort getEnderecoFuncionarioByCpfOutputPort) {
        this.getEnderecoFuncionarioByCpfOutputPort = getEnderecoFuncionarioByCpfOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoFuncionarioPorCpf(String cpf) {
        return getEnderecoFuncionarioByCpfOutputPort.getEnderecoFuncionarioByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário sem endereço cadastrado."));
    }
}

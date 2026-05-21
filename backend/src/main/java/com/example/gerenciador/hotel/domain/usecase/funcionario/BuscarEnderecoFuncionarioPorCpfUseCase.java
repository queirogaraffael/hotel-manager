package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarEnderecoFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoFuncionarioByCpfOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarEnderecoFuncionarioPorCpfUseCase implements BuscarEnderecoFuncionarioPorCpfInputPort {

    private final GetEnderecoFuncionarioByCpfOutputPort getEnderecoFuncionarioByCpfOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoFuncionarioPorCpf(String cpf) {
        return getEnderecoFuncionarioByCpfOutputPort.getEnderecoFuncionarioByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário sem endereço cadastrado."));
    }
}

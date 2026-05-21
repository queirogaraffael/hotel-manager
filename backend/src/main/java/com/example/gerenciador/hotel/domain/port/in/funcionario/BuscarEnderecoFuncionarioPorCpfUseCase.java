package com.example.gerenciador.hotel.domain.port.in.funcionario;

import com.example.gerenciador.hotel.domain.model.Endereco;

public interface BuscarEnderecoFuncionarioPorCpfUseCase {
    Endereco buscarEnderecoFuncionarioPorCpf(String cpf);
}

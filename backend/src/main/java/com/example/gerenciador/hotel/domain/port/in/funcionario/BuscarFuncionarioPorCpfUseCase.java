package com.example.gerenciador.hotel.domain.port.in.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;

public interface BuscarFuncionarioPorCpfUseCase {
    Funcionario buscarFuncionarioPorCpf(String cpf);
}

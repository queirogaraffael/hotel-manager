package com.example.gerenciador.hotel.domain.port.in.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;

public interface BuscarFuncionarioPorCpfInputPort {
    Funcionario buscarFuncionarioPorCpf(String cpf);
}

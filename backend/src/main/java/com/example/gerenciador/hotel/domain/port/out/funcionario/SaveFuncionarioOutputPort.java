package com.example.gerenciador.hotel.domain.port.out.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;

public interface SaveFuncionarioOutputPort {
    Funcionario save(Funcionario funcionario);
}

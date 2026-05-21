package com.example.gerenciador.hotel.domain.port.in.funcionario;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.model.Funcionario;

public interface CriarFuncionarioInputPort {
    Funcionario criarFuncionario(String cpf, String nome, String cargo, Turno turno);
}

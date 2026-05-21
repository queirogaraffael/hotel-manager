package com.example.gerenciador.hotel.domain.port.out.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import java.util.Optional;

public interface FindFuncionarioByIdOutputPort {
    Optional<Funcionario> findById(Long id);
}

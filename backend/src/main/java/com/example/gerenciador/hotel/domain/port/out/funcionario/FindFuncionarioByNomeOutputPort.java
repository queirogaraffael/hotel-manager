package com.example.gerenciador.hotel.domain.port.out.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindFuncionarioByNomeOutputPort {
    Page<Funcionario> findByNome(String nome, Pageable pageable);
}

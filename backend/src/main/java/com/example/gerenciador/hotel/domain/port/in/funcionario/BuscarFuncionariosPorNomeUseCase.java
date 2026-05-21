package com.example.gerenciador.hotel.domain.port.in.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarFuncionariosPorNomeUseCase {
    Page<Funcionario> buscarFuncionariosPorNome(String nome, Pageable pageable);
}

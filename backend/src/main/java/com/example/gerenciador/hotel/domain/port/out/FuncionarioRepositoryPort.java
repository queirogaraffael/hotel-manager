package com.example.gerenciador.hotel.domain.port.out;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FuncionarioRepositoryPort {

    Funcionario save(Funcionario funcionario);
    Optional<Funcionario> findById(Long id);
    Optional<Funcionario> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    Page<Funcionario> findByNome(String nome, Pageable pageable);
    Optional<Funcionario> findComExtratoByCpf(String cpf);
}

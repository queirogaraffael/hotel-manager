package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.funcionario;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.out.funcionario.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FuncionarioJpaAdapter implements
        SaveFuncionarioOutputPort,
        FindFuncionarioByIdOutputPort,
        FindFuncionarioByCpfOutputPort,
        ExistsFuncionarioByCpfOutputPort,
        FindFuncionarioByNomeOutputPort,
        FindComExtratoByCpfOutputPort {

    private final FuncionarioJpaRepository jpaRepository;
    private final FuncionarioEntityMapper mapper;

    @Override
    public Funcionario save(Funcionario funcionario) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(funcionario)));
    }

    @Override
    public Optional<Funcionario> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Funcionario> findByCpf(String cpf) {
        return jpaRepository.findByCpf(cpf).map(mapper::toDomain);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }

    @Override
    public Page<Funcionario> findByNome(String nome, Pageable pageable) {
        return jpaRepository.findByNome(nome, pageable).map(mapper::toDomain);
    }

    @Override
    public Optional<Funcionario> findComExtratoByCpf(String cpf) {
        return jpaRepository.findComExtratoByCpf(cpf).map(mapper::toDomain);
    }
}

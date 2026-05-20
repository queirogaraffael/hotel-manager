package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.port.out.ExtratoFuncionarioRepositoryPort;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper.ExtratoFuncionarioEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExtratoFuncionarioJpaAdapter implements ExtratoFuncionarioRepositoryPort {

    private final ExtratoFuncionarioJpaRepository jpaRepository;
    private final ExtratoFuncionarioEntityMapper mapper;

    @Override
    public ExtratoFuncionario save(ExtratoFuncionario extrato) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(extrato)));
    }

    @Override
    public Optional<ExtratoFuncionario> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<ExtratoFuncionario> findByCpfFuncionario(String cpf, Pageable pageable) {
        return jpaRepository.findByCpfFuncionario(cpf, pageable).map(mapper::toDomain);
    }

    @Override
    public boolean existeExtratoDeFuncionarioParaMes(String funcionarioCpf, Date dataExtrato) {
        return jpaRepository.existeExtratoDeFuncionarioParaMes(funcionarioCpf, dataExtrato);
    }
}

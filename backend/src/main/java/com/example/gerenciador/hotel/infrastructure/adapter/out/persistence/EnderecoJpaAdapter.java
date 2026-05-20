package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.out.EnderecoRepositoryPort;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper.EnderecoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EnderecoJpaAdapter implements EnderecoRepositoryPort {

    private final EnderecoJpaRepository jpaRepository;
    private final EnderecoEntityMapper mapper;

    @Override
    public Endereco save(Endereco endereco) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(endereco)));
    }

    @Override
    public Optional<Endereco> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Endereco> getEnderecoFuncionarioByCpf(String cpf) {
        return jpaRepository.getEnderecoFuncionarioByCpf(cpf).map(mapper::toDomain);
    }

    @Override
    public Optional<Endereco> getEnderecoHospedeByCpf(String cpf) {
        return jpaRepository.getEnderecoHospedeByCpf(cpf).map(mapper::toDomain);
    }
}

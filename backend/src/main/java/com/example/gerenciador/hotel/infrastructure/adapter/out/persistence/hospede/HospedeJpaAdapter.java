package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.out.hospede.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HospedeJpaAdapter implements
        SaveHospedeOutputPort,
        FindHospedeByIdOutputPort,
        FindHospedeByCpfOutputPort {

    private final HospedeJpaRepository jpaRepository;
    private final HospedeEntityMapper mapper;

    @Override
    public Hospede save(Hospede hospede) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(hospede)));
    }

    @Override
    public Optional<Hospede> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Hospede> findByCpf(String cpf) {
        return jpaRepository.findByCpf(cpf).map(mapper::toDomain);
    }
}

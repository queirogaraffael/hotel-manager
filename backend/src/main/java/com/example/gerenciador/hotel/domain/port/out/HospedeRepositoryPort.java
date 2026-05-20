package com.example.gerenciador.hotel.domain.port.out;

import com.example.gerenciador.hotel.domain.model.Hospede;

import java.util.Optional;

public interface HospedeRepositoryPort {

    Hospede save(Hospede hospede);
    Optional<Hospede> findById(Long id);
    Optional<Hospede> findByCpf(String cpf);
}

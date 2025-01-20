package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.entities.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    Optional<Hospede> findByCpf(String cpf);
}

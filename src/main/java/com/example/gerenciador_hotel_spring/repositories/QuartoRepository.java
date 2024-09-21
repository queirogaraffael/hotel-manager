package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.entities.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    Optional<Quarto> findByNumero(String numero);

    boolean existsByNumero(String numero);
}

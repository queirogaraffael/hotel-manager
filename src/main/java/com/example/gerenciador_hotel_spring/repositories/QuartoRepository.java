package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.entities.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
}

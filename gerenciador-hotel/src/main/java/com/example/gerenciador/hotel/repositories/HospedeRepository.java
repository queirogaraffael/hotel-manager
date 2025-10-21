package com.example.gerenciador.hotel.repositories;

import com.example.gerenciador.hotel.entities.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
        /*
    Optional<Hospede> findByCpf(String cpf);
         */
}

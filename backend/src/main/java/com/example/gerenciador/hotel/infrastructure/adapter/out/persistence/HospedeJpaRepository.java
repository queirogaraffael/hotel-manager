package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence;

import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.HospedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospedeJpaRepository extends JpaRepository<HospedeEntity, Long> {

    @Query("SELECT h FROM HospedeEntity h JOIN h.user u WHERE u.cpf = :cpf")
    Optional<HospedeEntity> findByCpf(@Param("cpf") String cpf);
}

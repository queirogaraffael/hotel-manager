package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.dtos.ExtratoFuncionarioDTO;
import com.example.gerenciador_hotel_spring.entities.ExtratoFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository
public interface ExtratoFuncionarioRepository extends JpaRepository<ExtratoFuncionario, Long> {

    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.ExtratoFuncionarioDTO(e.id, e.mesReferente) FROM ExtratoFuncionario e WHERE e.funcionario.cpf = :cpf")
    Page<ExtratoFuncionarioDTO> findExtratosFuncionarioDTOByCPF(@Param("cpf") String cpf, Pageable pageable);


    @Query("SELECT COUNT(e) > 0 FROM ExtratoFuncionario e WHERE e.funcionario.cpf = :funcionarioCPF AND e.mesReferente = :data")
    boolean existeExtratoDeFuncionarioParaMes(@Param("funcionarioCPF") String funcionarioCPF, @Param("data") YearMonth mesReferente);
}


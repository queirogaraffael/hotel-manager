package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.dtos.extratoFuncionario.ExtratoFuncionarioResponseDTO;
import com.example.gerenciador_hotel_spring.entities.ExtratoFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExtratoFuncionarioRepository extends JpaRepository<ExtratoFuncionario, Long> {

    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.extratoFuncionario.ExtratoFuncionarioResponseDTO(e.id, e.dataExtrato) FROM ExtratoFuncionario e WHERE e.funcionario.cpf = :cpf")
    Page<ExtratoFuncionarioResponseDTO> findExtratosFuncionarioDTOByCPF(@Param("cpf") String cpf, Pageable pageable);


    @Query("SELECT COUNT(e) > 0 FROM ExtratoFuncionario e " +
            "WHERE e.funcionario.cpf = :funcionarioCPF " +
            "AND EXTRACT(YEAR FROM e.dataExtrato) = EXTRACT(YEAR FROM :data) " +
            "AND EXTRACT(MONTH FROM e.dataExtrato) = EXTRACT(MONTH FROM :data)")
    boolean existeExtratoDeFuncionarioParaMes(@Param("funcionarioCPF") String funcionarioCPF, @Param("data") Date dataExtrato);

}


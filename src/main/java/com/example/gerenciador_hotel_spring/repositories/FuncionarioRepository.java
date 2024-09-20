package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.dtos.FuncionarioDTO;
import com.example.gerenciador_hotel_spring.entities.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByCpf(String cpf);

    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.FuncionarioDTO(f.id, f.nome, f.cpf) FROM Funcionario f WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<FuncionarioDTO> findFuncionariosDTOByNamePageados(@Param("nome") String nome, Pageable pageable);

    boolean existsByCpf(String cpf);

    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.extratoFuncionario WHERE f.cpf = :cpf")
    Optional<Funcionario> findFuncionarioComExtratoByCPF(@Param("cpf") String cpf);

}

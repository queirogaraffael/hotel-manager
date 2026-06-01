package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioJpaRepository extends JpaRepository<FuncionarioEntity, Long> {

    @Query("SELECT f FROM FuncionarioEntity f JOIN f.user u WHERE u.cpf = :cpf")
    Optional<FuncionarioEntity> findByCpf(@Param("cpf") String cpf);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FuncionarioEntity f JOIN f.user u WHERE u.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);

    @Query("SELECT f FROM FuncionarioEntity f JOIN f.user u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<FuncionarioEntity> findByNome(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT f FROM FuncionarioEntity f LEFT JOIN FETCH f.extratoFuncionario JOIN f.user u WHERE u.cpf = :cpf")
    Optional<FuncionarioEntity> findComExtratoByCpf(@Param("cpf") String cpf);
}

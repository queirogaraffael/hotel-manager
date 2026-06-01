package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoJpaRepository extends JpaRepository<EnderecoEntity, Long> {

    @Query("SELECT e FROM EnderecoEntity e JOIN e.user u JOIN u.funcionario f JOIN f.user fu WHERE fu.cpf = :cpf")
    Optional<EnderecoEntity> getEnderecoFuncionarioByCpf(@Param("cpf") String cpf);

    @Query("SELECT e FROM EnderecoEntity e JOIN e.user u JOIN u.hospede h JOIN h.user hu WHERE hu.cpf = :cpf")
    Optional<EnderecoEntity> getEnderecoHospedeByCpf(@Param("cpf") String cpf);
}

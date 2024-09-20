package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e JOIN e.pessoa p WHERE TYPE(p) = Funcionario AND TREAT(p AS Funcionario).cpf = :cpf")
    Optional<Endereco> getEnderecoFuncionarioByCPF(@Param("cpf") String cpf);

    @Query("SELECT e FROM Endereco e JOIN e.pessoa p WHERE TYPE(p) = Hospede AND TREAT(p AS Hospede).cpf = :cpf")
    Optional<Endereco> getEnderecoHospedeByCPF(@Param("cpf") String cpf);

}

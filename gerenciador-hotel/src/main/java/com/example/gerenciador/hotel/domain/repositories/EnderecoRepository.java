package com.example.gerenciador.hotel.domain.repositories;


import com.example.gerenciador.hotel.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    /*
    @Query("SELECT e FROM Endereco e JOIN e.pessoa p WHERE TYPE(p) = Funcionario AND TREAT(p AS Funcionario).cpf = :cpf")
    Optional<Endereco> getEnderecoFuncionarioByCPF(@Param("cpf") String cpf);

    @Query("SELECT e FROM Endereco e JOIN e.pessoa p WHERE TYPE(p) = Hospede AND TREAT(p AS Hospede).cpf = :cpf")
    Optional<Endereco> getEnderecoHospedeByCPF(@Param("cpf") String cpf);


     */
}

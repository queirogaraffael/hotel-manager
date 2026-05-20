package com.example.gerenciador.hotel.domain.port.out;

import com.example.gerenciador.hotel.domain.model.Endereco;

import java.util.Optional;

public interface EnderecoRepositoryPort {

    Endereco save(Endereco endereco);
    Optional<Endereco> findById(Long id);
    Optional<Endereco> getEnderecoFuncionarioByCpf(String cpf);
    Optional<Endereco> getEnderecoHospedeByCpf(String cpf);
}

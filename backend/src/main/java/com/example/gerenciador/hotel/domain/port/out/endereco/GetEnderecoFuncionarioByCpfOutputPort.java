package com.example.gerenciador.hotel.domain.port.out.endereco;

import com.example.gerenciador.hotel.domain.model.Endereco;
import java.util.Optional;

public interface GetEnderecoFuncionarioByCpfOutputPort {
    Optional<Endereco> getEnderecoFuncionarioByCpf(String cpf);
}

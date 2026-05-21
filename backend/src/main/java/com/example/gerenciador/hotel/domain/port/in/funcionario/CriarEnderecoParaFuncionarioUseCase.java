package com.example.gerenciador.hotel.domain.port.in.funcionario;

import com.example.gerenciador.hotel.domain.model.Endereco;

public interface CriarEnderecoParaFuncionarioUseCase {
    Endereco criarEnderecoParaFuncionario(String cpf, Endereco endereco);
}

package com.example.gerenciador.hotel.domain.port.in;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioUseCase {

    Funcionario criarFuncionario(String cpf, String nome, String cargo, Turno turno);
    Funcionario buscarFuncionarioPorCpf(String cpf);
    Funcionario editarFuncionario(String cpf, String nome, String cargo, Turno turno);
    Page<Funcionario> buscarFuncionariosPorNome(String nome, Pageable pageable);
    Endereco criarEnderecoParaFuncionario(String cpf, Endereco endereco);
    Endereco buscarEnderecoFuncionarioPorCpf(String cpf);
}


package com.example.gerenciador.hotel.domain.port.in;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Hospede;

public interface HospedeUseCase {

    Hospede criarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone);
    Hospede buscarHospedePorCpf(String cpf);
    Hospede editarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone);
    Endereco criarEnderecoParaHospede(String cpf, Endereco endereco);
    Endereco buscarEnderecoHospedePorCpf(String cpf);
}


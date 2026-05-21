package com.example.gerenciador.hotel.domain.port.in.hospede;

import com.example.gerenciador.hotel.domain.model.Endereco;

public interface CriarEnderecoParaHospedeInputPort {
    Endereco criarEnderecoParaHospede(String cpf, Endereco endereco);
}

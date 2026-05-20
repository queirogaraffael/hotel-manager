package com.example.gerenciador.hotel.domain.port.in;

import com.example.gerenciador.hotel.domain.model.Endereco;

public interface EnderecoUseCase {

    Endereco modificarEndereco(Long id, Endereco enderecoModificado);
}


package com.example.gerenciador.hotel.domain.port.in.endereco;

import com.example.gerenciador.hotel.domain.model.Endereco;

public interface ModificarEnderecoUseCase {
    Endereco modificarEndereco(Long id, Endereco enderecoModificado);
}

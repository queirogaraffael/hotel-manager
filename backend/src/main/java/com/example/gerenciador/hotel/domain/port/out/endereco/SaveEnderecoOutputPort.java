package com.example.gerenciador.hotel.domain.port.out.endereco;

import com.example.gerenciador.hotel.domain.model.Endereco;

public interface SaveEnderecoOutputPort {
    Endereco save(Endereco endereco);
}

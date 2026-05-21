package com.example.gerenciador.hotel.domain.port.in.hospede;

import com.example.gerenciador.hotel.domain.model.Endereco;

public interface BuscarEnderecoHospedePorCpfInputPort {
    Endereco buscarEnderecoHospedePorCpf(String cpf);
}

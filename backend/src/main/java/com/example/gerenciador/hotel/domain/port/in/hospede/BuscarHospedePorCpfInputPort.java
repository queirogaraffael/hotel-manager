package com.example.gerenciador.hotel.domain.port.in.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;

public interface BuscarHospedePorCpfInputPort {
    Hospede buscarHospedePorCpf(String cpf);
}

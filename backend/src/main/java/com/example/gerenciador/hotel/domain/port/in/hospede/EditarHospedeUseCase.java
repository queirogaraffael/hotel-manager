package com.example.gerenciador.hotel.domain.port.in.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;

public interface EditarHospedeUseCase {
    Hospede editarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone);
}

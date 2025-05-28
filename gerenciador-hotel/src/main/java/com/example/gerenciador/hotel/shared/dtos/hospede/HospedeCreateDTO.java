package com.example.gerenciador.hotel.shared.dtos.hospede;

import jakarta.validation.constraints.Size;

import java.util.Date;

public record HospedeCreateDTO(@Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres") String cpf, String nome, Date dataNascimento, String numeroTelefone) {
}

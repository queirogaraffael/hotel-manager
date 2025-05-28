package com.example.gerenciador.hotel.shared.dtos.hospede;

import java.util.Date;

public record HospedeUpdateDTO(String nome, Date dataNascimento, String numeroTelefone) {
}

package com.example.gerenciador.hotel.dtos.hospede;

import java.util.Date;

public record HospedeUpdateDTO(String nome, Date dataNascimento, String numeroTelefone) {
}

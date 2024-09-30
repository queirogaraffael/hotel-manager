package com.example.gerenciador_hotel_spring.dtos.hospede;

import java.util.Date;

public record HospedeUpdateDTO(String nome, Date dataNascimento, String numeroTelefone) {
}

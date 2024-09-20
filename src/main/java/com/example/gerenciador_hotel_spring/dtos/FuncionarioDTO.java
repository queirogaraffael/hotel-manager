package com.example.gerenciador_hotel_spring.dtos;

import com.example.gerenciador_hotel_spring.enums.Turno;

import java.util.Date;

public record FuncionarioDTO(Long id, String nome,
                             String cpf) {
}

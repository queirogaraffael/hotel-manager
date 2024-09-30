package com.example.gerenciador_hotel_spring.dtos.funcionario;

import com.example.gerenciador_hotel_spring.enums.Turno;

import java.util.Date;

public record FuncionarioUpdateDTO(String nome, Date dataNascimento, String numeroTelefone, String cargo
        , Turno turno) {
}

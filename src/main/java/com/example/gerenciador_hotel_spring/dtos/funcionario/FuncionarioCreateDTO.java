package com.example.gerenciador_hotel_spring.dtos.funcionario;

import com.example.gerenciador_hotel_spring.enums.Turno;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record FuncionarioCreateDTO(@Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres") String cpf, String nome, Date dataNascimento, String numeroTelefone, String cargo
, Turno turno) {
}


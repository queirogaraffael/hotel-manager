package com.example.gerenciador.hotel.dtos.funcionario;


import com.example.gerenciador.hotel.enums.Turno;

import java.util.Date;

public record FuncionarioUpdateDTO(String nome, Date dataNascimento, String numeroTelefone, String cargo
        , Turno turno) {
}

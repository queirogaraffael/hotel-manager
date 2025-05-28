package com.example.gerenciador.hotel.shared.dtos.funcionario;



import com.example.gerenciador.hotel.domain.enums.Turno;

import java.util.Date;

public record FuncionarioUpdateDTO(String nome, Date dataNascimento, String numeroTelefone, String cargo
        , Turno turno) {
}

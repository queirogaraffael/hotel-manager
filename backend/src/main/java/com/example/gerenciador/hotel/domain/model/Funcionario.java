package com.example.gerenciador.hotel.domain.model;

import com.example.gerenciador.hotel.domain.enums.Turno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    private Long id;
    private String cargo;
    private Turno turno;
    private User user;

    @Builder.Default
    private List<ExtratoFuncionario> extratoFuncionario = new ArrayList<>();
}

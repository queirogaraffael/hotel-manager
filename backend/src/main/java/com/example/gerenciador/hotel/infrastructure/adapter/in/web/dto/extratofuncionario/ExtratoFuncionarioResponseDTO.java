package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.extratofuncionario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoFuncionarioResponseDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataExtrato;

    private double horasTrabalhadas;
    private double valorHora;
    private double salario;
}

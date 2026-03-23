package com.example.gerenciador.hotel.shared.dtos.extratofuncionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long funcionarioId;
}
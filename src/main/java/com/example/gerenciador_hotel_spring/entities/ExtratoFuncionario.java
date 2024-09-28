package com.example.gerenciador_hotel_spring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExtratoFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    private Date dataExtrato;

    private double horasTrabalhadas;
    private double valorHora;
    private double salario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @NotNull(message = "ExtratoFuncionario precisa estar associado a um funcionário.")
    private Funcionario funcionario;

}
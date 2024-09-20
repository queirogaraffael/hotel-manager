package com.example.gerenciador_hotel_spring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExtratoFuncionario {

    public static final String FORMATO_DATA = "MM/yyyy";
    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_DATA);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    private YearMonth mesReferente;

    private double horasTrabalhadas;
    private double valorHora;
    private double salario;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

}
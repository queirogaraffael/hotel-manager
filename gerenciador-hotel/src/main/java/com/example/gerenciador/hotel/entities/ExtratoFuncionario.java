package com.example.gerenciador.hotel.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataExtrato;

    @Column(nullable = false)
    @PositiveOrZero(message = "Horas trabalhadas não podem ser negativas")
    private double horasTrabalhadas;

    @Column(nullable = false)
    @Positive(message = "Valor da hora deve ser maior que zero")
    private double valorHora;

    @Column(nullable = false)
    @PositiveOrZero(message = "Salário não pode ser negativo")
    private double salario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    @NotNull(message = "Funcionário não pode ser nulo")
    private Funcionario funcionario;
}

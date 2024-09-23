package com.example.gerenciador_hotel_spring.entities;


import com.example.gerenciador_hotel_spring.enums.Turno;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Funcionario extends Pessoa {

    @Column(unique = true)
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
    @EqualsAndHashCode.Include
    private String cpf;

    private String cargo;
    private Turno turno;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExtratoFuncionario> extratoFuncionario = new HashSet<>();

}
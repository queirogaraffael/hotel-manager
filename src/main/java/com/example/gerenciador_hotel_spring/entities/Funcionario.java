package com.example.gerenciador_hotel_spring.entities;


import com.example.gerenciador_hotel_spring.enums.Turno;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
    private String cpf;

    private String cargo;
    private Turno turno;


    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExtratoFuncionario> extratoFuncionario = new HashSet<>();

}
package com.example.gerenciador_hotel_spring.entities;


import com.example.gerenciador_hotel_spring.enums.StatusQuarto;
import com.example.gerenciador_hotel_spring.enums.TipoQuarto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String numero;

    private TipoQuarto tipoQuarto;
    private int capacidade;
    private double precoDiaria;
    private StatusQuarto statusQuarto;

    @JsonIgnore
    @OneToMany(mappedBy = "quarto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas = new HashSet<>();
}
package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quarto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuartoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipoQuarto;

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false)
    private double precoDiaria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusQuarto statusQuarto;

    @OneToMany(mappedBy = "quarto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservaEntity> reservas = new ArrayList<>();
}

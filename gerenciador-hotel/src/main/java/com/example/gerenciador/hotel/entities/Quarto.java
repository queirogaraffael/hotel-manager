package com.example.gerenciador.hotel.entities;


import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    @NotBlank(message = "Número do quarto é obrigatório")
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Tipo do quarto é obrigatório")
    private TipoQuarto tipoQuarto;

    @Column(nullable = false)
    @Min(value = 1, message = "Capacidade mínima deve ser 1")
    private int capacidade;

    @Column(nullable = false)
    @Positive(message = "Preço da diária deve ser maior que zero")
    private double precoDiaria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Status do quarto é obrigatório")
    private StatusQuarto statusQuarto;

    @OneToMany(mappedBy = "quarto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();
}

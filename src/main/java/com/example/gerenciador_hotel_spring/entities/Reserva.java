package com.example.gerenciador_hotel_spring.entities;


import com.example.gerenciador_hotel_spring.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Date dataEntrada;
    private Date dataSaida;

    private Integer numeroHospedes;

    private StatusReserva statusReserva;

    private Double valorTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;

}
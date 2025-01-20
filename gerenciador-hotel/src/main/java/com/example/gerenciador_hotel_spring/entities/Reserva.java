package com.example.gerenciador_hotel_spring.entities;


import com.example.gerenciador_hotel_spring.enums.StatusReserva;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quarto_id")
    @NotNull(message = "Reserva precisa estar associada a um quarto.")
    private Quarto quarto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hospede_id")
    @NotNull(message = "Reserva precisa estar associada a um hospede.")
    private Hospede hospede;

}
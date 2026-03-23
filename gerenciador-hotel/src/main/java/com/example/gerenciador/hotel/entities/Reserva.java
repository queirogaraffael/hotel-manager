package com.example.gerenciador.hotel.entities;


import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private Integer numeroHospedes;
    private StatusReserva statusReserva;
    private Double valorTotal;


    @ManyToOne
    @JoinColumn(name = "quarto_id")
    @NotNull(message = "Reserva precisa estar associada a um quarto.")
    private Quarto quarto;


    @ManyToOne
    @JoinColumn(name = "hospede_id")
    @NotNull(message = "Reserva precisa estar associada a um hospede.")
    private Hospede hospede;

}
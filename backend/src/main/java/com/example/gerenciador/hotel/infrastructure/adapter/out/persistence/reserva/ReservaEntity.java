package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.hospede.HospedeEntity;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.quarto.QuartoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private Integer numeroHospedes;

    @Enumerated(EnumType.STRING)
    private StatusReserva statusReserva;

    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private QuartoEntity quarto;

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private HospedeEntity hospede;
}

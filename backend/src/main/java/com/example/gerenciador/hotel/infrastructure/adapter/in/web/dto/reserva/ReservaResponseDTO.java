package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataEntrada;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataSaida;

    private Integer numeroHospedes;
    private StatusReserva statusReserva;
    private Double valorTotal;
    private String numeroQuarto;
    private String cpfHospede;
}

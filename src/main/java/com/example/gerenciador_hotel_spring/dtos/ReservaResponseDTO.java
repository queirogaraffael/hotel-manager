package com.example.gerenciador_hotel_spring.dtos;

import com.example.gerenciador_hotel_spring.enums.StatusReserva;

import java.util.Date;

public record ReservaResponseDTO(Long id, Date dataEntrada, Date dataSaida, StatusReserva statusReserva) {
}

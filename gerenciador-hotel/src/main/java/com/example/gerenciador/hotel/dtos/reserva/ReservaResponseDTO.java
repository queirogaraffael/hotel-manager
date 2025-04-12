package com.example.gerenciador.hotel.dtos.reserva;


import com.example.gerenciador.hotel.enums.StatusReserva;

import java.util.Date;

public record ReservaResponseDTO(Long id, Date dataEntrada, Date dataSaida, StatusReserva statusReserva) {
}

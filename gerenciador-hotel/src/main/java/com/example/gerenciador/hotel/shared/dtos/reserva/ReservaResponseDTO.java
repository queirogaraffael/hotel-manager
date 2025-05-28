package com.example.gerenciador.hotel.shared.dtos.reserva;



import com.example.gerenciador.hotel.domain.enums.StatusReserva;

import java.util.Date;

public record ReservaResponseDTO(Long id, Date dataEntrada, Date dataSaida, StatusReserva statusReserva) {
}

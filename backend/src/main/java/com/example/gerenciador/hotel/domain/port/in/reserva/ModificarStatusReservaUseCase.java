package com.example.gerenciador.hotel.domain.port.in.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;

public interface ModificarStatusReservaUseCase {
    Reserva modificarStatusReserva(Long id, StatusReserva statusReserva);
}

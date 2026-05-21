package com.example.gerenciador.hotel.domain.port.in.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;

public interface BuscarReservaPorIdUseCase {
    Reserva buscarReservaPorId(Long id);
}

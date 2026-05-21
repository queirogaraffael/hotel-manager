package com.example.gerenciador.hotel.domain.port.out.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;

public interface SaveReservaOutputPort {
    Reserva save(Reserva reserva);
}

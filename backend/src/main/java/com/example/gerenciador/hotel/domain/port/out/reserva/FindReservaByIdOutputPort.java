package com.example.gerenciador.hotel.domain.port.out.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import java.util.Optional;

public interface FindReservaByIdOutputPort {
    Optional<Reserva> findById(Long id);
}

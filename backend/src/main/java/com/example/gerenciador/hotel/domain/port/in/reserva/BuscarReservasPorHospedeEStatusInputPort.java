package com.example.gerenciador.hotel.domain.port.in.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarReservasPorHospedeEStatusInputPort {
    Page<Reserva> buscarReservasPorHospedeEStatus(String cpf, StatusReserva status, Pageable pageable);
}

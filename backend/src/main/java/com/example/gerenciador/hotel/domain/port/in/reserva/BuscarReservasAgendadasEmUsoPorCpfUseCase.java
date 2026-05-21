package com.example.gerenciador.hotel.domain.port.in.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarReservasAgendadasEmUsoPorCpfUseCase {
    Page<Reserva> buscarReservasAgendadasEmUsoPorCpf(String cpf, Pageable pageable);
}

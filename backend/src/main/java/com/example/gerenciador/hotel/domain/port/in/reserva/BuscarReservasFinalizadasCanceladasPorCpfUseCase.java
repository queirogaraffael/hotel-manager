package com.example.gerenciador.hotel.domain.port.in.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarReservasFinalizadasCanceladasPorCpfUseCase {
    Page<Reserva> buscarReservasFinalizadasCanceladasPorCpf(String cpf, Pageable pageable);
}

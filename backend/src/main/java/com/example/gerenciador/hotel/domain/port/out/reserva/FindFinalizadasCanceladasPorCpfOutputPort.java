package com.example.gerenciador.hotel.domain.port.out.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindFinalizadasCanceladasPorCpfOutputPort {
    Page<Reserva> findFinalizadasCanceladasPorCpf(String cpf, Pageable pageable);
}

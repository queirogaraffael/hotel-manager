package com.example.gerenciador.hotel.domain.usecase.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.BuscarReservasFinalizadasCanceladasPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindFinalizadasCanceladasPorCpfOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarReservasFinalizadasCanceladasPorCpfUseCase implements BuscarReservasFinalizadasCanceladasPorCpfInputPort {

    private final FindFinalizadasCanceladasPorCpfOutputPort findFinalizadasCanceladasPorCpfOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasFinalizadasCanceladasPorCpf(String cpf, Pageable pageable) {
        return findFinalizadasCanceladasPorCpfOutputPort.findFinalizadasCanceladasPorCpf(cpf, pageable);
    }
}

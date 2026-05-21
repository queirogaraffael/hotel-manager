package com.example.gerenciador.hotel.domain.usecase.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.BuscarReservasAgendadasEmUsoPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindAgendadasEmUsoPorCpfOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class BuscarReservasAgendadasEmUsoPorCpfUseCase implements BuscarReservasAgendadasEmUsoPorCpfInputPort {

    private final FindAgendadasEmUsoPorCpfOutputPort findAgendadasEmUsoPorCpfOutputPort;

    public BuscarReservasAgendadasEmUsoPorCpfUseCase(FindAgendadasEmUsoPorCpfOutputPort findAgendadasEmUsoPorCpfOutputPort) {
        this.findAgendadasEmUsoPorCpfOutputPort = findAgendadasEmUsoPorCpfOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasAgendadasEmUsoPorCpf(String cpf, Pageable pageable) {
        return findAgendadasEmUsoPorCpfOutputPort.findAgendadasEmUsoPorCpf(cpf, pageable);
    }
}

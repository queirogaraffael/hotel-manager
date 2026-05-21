package com.example.gerenciador.hotel.domain.usecase.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.BuscarReservaPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindReservaByIdOutputPort;
import com.example.gerenciador.hotel.domain.exception.ReservaNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BuscarReservaPorIdUseCase implements BuscarReservaPorIdInputPort {

    private final FindReservaByIdOutputPort findReservaByIdOutputPort;

    public BuscarReservaPorIdUseCase(FindReservaByIdOutputPort findReservaByIdOutputPort) {
        this.findReservaByIdOutputPort = findReservaByIdOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Reserva buscarReservaPorId(Long id) {
        return findReservaByIdOutputPort.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva com id " + id + " não encontrada."));
    }
}

package com.example.gerenciador.hotel.domain.usecase.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.BuscarReservaPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.in.reserva.ModificarStatusReservaInputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.SaveReservaOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModificarStatusReservaUseCase implements ModificarStatusReservaInputPort {

    private final BuscarReservaPorIdInputPort buscarReservaPorIdInputPort;
    private final SaveReservaOutputPort saveReservaOutputPort;

    @Override
    @Transactional
    public Reserva modificarStatusReserva(Long id, StatusReserva statusReserva) {
        Reserva reserva = buscarReservaPorIdInputPort.buscarReservaPorId(id);
        reserva.setStatusReserva(statusReserva);
        return saveReservaOutputPort.save(reserva);
    }
}

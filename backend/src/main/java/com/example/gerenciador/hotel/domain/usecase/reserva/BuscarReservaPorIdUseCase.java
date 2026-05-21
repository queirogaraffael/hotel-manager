package com.example.gerenciador.hotel.domain.usecase.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.BuscarReservaPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindReservaByIdOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarReservaPorIdUseCase implements BuscarReservaPorIdInputPort {

    private final FindReservaByIdOutputPort findReservaByIdOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Reserva buscarReservaPorId(Long id) {
        return findReservaByIdOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva com id " + id + " não encontrada."));
    }
}

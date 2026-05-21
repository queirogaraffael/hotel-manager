package com.example.gerenciador.hotel.domain.usecase.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.BuscarReservasPorHospedeEStatusInputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindByHospedeEStatusOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class BuscarReservasPorHospedeEStatusUseCase implements BuscarReservasPorHospedeEStatusInputPort {

    private final FindByHospedeEStatusOutputPort findByHospedeEStatusOutputPort;

    public BuscarReservasPorHospedeEStatusUseCase(FindByHospedeEStatusOutputPort findByHospedeEStatusOutputPort) {
        this.findByHospedeEStatusOutputPort = findByHospedeEStatusOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasPorHospedeEStatus(String cpf, StatusReserva status, Pageable pageable) {
        return findByHospedeEStatusOutputPort.findByHospedeEStatus(cpf, status, pageable);
    }
}

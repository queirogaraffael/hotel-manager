package com.example.gerenciador.hotel.domain.port.in;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ReservaUseCase {

    Reserva criarReserva(String cpfHospede, String numeroQuarto, LocalDateTime dataEntrada, LocalDateTime dataSaida, Integer numeroHospedes);
    Reserva buscarReservaPorId(Long id);
    Reserva modificarStatusReserva(Long id, StatusReserva statusReserva);
    Page<Reserva> buscarReservasPorHospedeEStatus(String cpf, StatusReserva status, Pageable pageable);
    Page<Reserva> buscarReservasAgendadasEmUsoPorCpf(String cpf, Pageable pageable);
    Page<Reserva> buscarReservasFinalizadasCanceladasPorCpf(String cpf, Pageable pageable);
}


package com.example.gerenciador.hotel.domain.port.out;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservaRepositoryPort {

    Reserva save(Reserva reserva);
    Optional<Reserva> findById(Long id);
    boolean quartoEstaDisponivelParaData(LocalDateTime dataEntrada, LocalDateTime dataSaida, String numeroQuarto);
    Page<Reserva> findByHospedeEStatus(String cpf, StatusReserva status, Pageable pageable);
    Page<Reserva> findAgendadasEmUsoPorCpf(String cpf, Pageable pageable);
    Page<Reserva> findFinalizadasCanceladasPorCpf(String cpf, Pageable pageable);
}

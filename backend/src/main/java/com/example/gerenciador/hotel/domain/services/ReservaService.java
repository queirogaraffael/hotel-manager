package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.ReservaUseCase;
import com.example.gerenciador.hotel.domain.port.out.HospedeRepositoryPort;
import com.example.gerenciador.hotel.domain.port.out.QuartoRepositoryPort;
import com.example.gerenciador.hotel.domain.port.out.ReservaRepositoryPort;
import com.example.gerenciador.hotel.shared.exceptions.QuartoNaoEstaDisponivelParaDataException;
import com.example.gerenciador.hotel.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservaService implements ReservaUseCase {

    private final ReservaRepositoryPort reservaRepository;
    private final HospedeRepositoryPort hospedeRepository;
    private final QuartoRepositoryPort quartoRepository;

    @Override
    @Transactional
    public Reserva criarReserva(String cpfHospede, String numeroQuarto, LocalDateTime dataEntrada, LocalDateTime dataSaida, Integer numeroHospedes) {
        Quarto quarto = quartoRepository.findByNumero(numeroQuarto)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto com número " + numeroQuarto + " não encontrado."));

        boolean disponivel = reservaRepository.quartoEstaDisponivelParaData(dataEntrada, dataSaida, numeroQuarto);
        if (!disponivel) {
            throw new QuartoNaoEstaDisponivelParaDataException("Quarto não está disponível para reserva nesta data.");
        }

        Hospede hospede = hospedeRepository.findByCpf(cpfHospede)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com CPF " + cpfHospede + " não encontrado."));

        Reserva reserva = Reserva.builder()
                .dataEntrada(dataEntrada)
                .dataSaida(dataSaida)
                .numeroHospedes(numeroHospedes)
                .statusReserva(StatusReserva.AGENDADO)
                .quarto(quarto)
                .hospede(hospede)
                .build();

        return reservaRepository.save(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public Reserva buscarReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva com id " + id + " não encontrada."));
    }

    @Override
    @Transactional
    public Reserva modificarStatusReserva(Long id, StatusReserva statusReserva) {
        Reserva reserva = buscarReservaPorId(id);
        reserva.setStatusReserva(statusReserva);
        return reservaRepository.save(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasPorHospedeEStatus(String cpf, StatusReserva status, Pageable pageable) {
        return reservaRepository.findByHospedeEStatus(cpf, status, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasAgendadasEmUsoPorCpf(String cpf, Pageable pageable) {
        return reservaRepository.findAgendadasEmUsoPorCpf(cpf, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasFinalizadasCanceladasPorCpf(String cpf, Pageable pageable) {
        return reservaRepository.findFinalizadasCanceladasPorCpf(cpf, pageable);
    }
}

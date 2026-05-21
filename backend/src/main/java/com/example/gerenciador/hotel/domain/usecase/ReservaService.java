package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.*;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindQuartoByNumeroOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.*;
import com.example.gerenciador.hotel.shared.exception.QuartoNaoEstaDisponivelParaDataException;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservaService implements
        CriarReservaInputPort,
        BuscarReservaPorIdInputPort,
        ModificarStatusReservaInputPort,
        BuscarReservasPorHospedeEStatusInputPort,
        BuscarReservasAgendadasEmUsoPorCpfInputPort,
        BuscarReservasFinalizadasCanceladasPorCpfInputPort {

    private final SaveReservaOutputPort saveReservaOutputPort;
    private final FindReservaByIdOutputPort findReservaByIdOutputPort;
    private final QuartoEstaDisponivelParaDataOutputPort quartoEstaDisponivelParaDataOutputPort;
    private final FindByHospedeEStatusOutputPort findByHospedeEStatusOutputPort;
    private final FindAgendadasEmUsoPorCpfOutputPort findAgendadasEmUsoPorCpfOutputPort;
    private final FindFinalizadasCanceladasPorCpfOutputPort findFinalizadasCanceladasPorCpfOutputPort;
    private final FindHospedeByCpfOutputPort findHospedeByCpfOutputPort;
    private final FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort;

    @Override
    @Transactional
    public Reserva criarReserva(String cpfHospede, String numeroQuarto, LocalDateTime dataEntrada, LocalDateTime dataSaida, Integer numeroHospedes) {
        Quarto quarto = findQuartoByNumeroOutputPort.findByNumero(numeroQuarto)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto com número " + numeroQuarto + " não encontrado."));

        boolean disponivel = quartoEstaDisponivelParaDataOutputPort.quartoEstaDisponivelParaData(dataEntrada, dataSaida, numeroQuarto);
        if (!disponivel) {
            throw new QuartoNaoEstaDisponivelParaDataException("Quarto não está disponível para reserva nesta data.");
        }

        Hospede hospede = findHospedeByCpfOutputPort.findByCpf(cpfHospede)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com CPF " + cpfHospede + " não encontrado."));

        Reserva reserva = Reserva.builder()
                .dataEntrada(dataEntrada)
                .dataSaida(dataSaida)
                .numeroHospedes(numeroHospedes)
                .statusReserva(StatusReserva.AGENDADO)
                .quarto(quarto)
                .hospede(hospede)
                .build();

        return saveReservaOutputPort.save(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public Reserva buscarReservaPorId(Long id) {
        return findReservaByIdOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva com id " + id + " não encontrada."));
    }

    @Override
    @Transactional
    public Reserva modificarStatusReserva(Long id, StatusReserva statusReserva) {
        Reserva reserva = buscarReservaPorId(id);
        reserva.setStatusReserva(statusReserva);
        return saveReservaOutputPort.save(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasPorHospedeEStatus(String cpf, StatusReserva status, Pageable pageable) {
        return findByHospedeEStatusOutputPort.findByHospedeEStatus(cpf, status, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasAgendadasEmUsoPorCpf(String cpf, Pageable pageable) {
        return findAgendadasEmUsoPorCpfOutputPort.findAgendadasEmUsoPorCpf(cpf, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> buscarReservasFinalizadasCanceladasPorCpf(String cpf, Pageable pageable) {
        return findFinalizadasCanceladasPorCpfOutputPort.findFinalizadasCanceladasPorCpf(cpf, pageable);
    }
}

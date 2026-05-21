package com.example.gerenciador.hotel.domain.usecase.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.reserva.CriarReservaInputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindQuartoByNumeroOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.QuartoEstaDisponivelParaDataOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.SaveReservaOutputPort;
import com.example.gerenciador.hotel.shared.exception.QuartoNaoEstaDisponivelParaDataException;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public class CriarReservaUseCase implements CriarReservaInputPort {

    private final FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort;
    private final QuartoEstaDisponivelParaDataOutputPort quartoEstaDisponivelParaDataOutputPort;
    private final FindHospedeByCpfOutputPort findHospedeByCpfOutputPort;
    private final SaveReservaOutputPort saveReservaOutputPort;

    public CriarReservaUseCase(
            FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort,
            QuartoEstaDisponivelParaDataOutputPort quartoEstaDisponivelParaDataOutputPort,
            FindHospedeByCpfOutputPort findHospedeByCpfOutputPort,
            SaveReservaOutputPort saveReservaOutputPort
    ) {
        this.findQuartoByNumeroOutputPort = findQuartoByNumeroOutputPort;
        this.quartoEstaDisponivelParaDataOutputPort = quartoEstaDisponivelParaDataOutputPort;
        this.findHospedeByCpfOutputPort = findHospedeByCpfOutputPort;
        this.saveReservaOutputPort = saveReservaOutputPort;
    }

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
}

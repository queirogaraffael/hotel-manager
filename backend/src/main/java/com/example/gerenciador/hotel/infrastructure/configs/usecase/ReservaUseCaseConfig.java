package com.example.gerenciador.hotel.infrastructure.configs.usecase;

import com.example.gerenciador.hotel.domain.port.in.reserva.BuscarReservaPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindQuartoByNumeroOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindAgendadasEmUsoPorCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindByHospedeEStatusOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindFinalizadasCanceladasPorCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.FindReservaByIdOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.QuartoEstaDisponivelParaDataOutputPort;
import com.example.gerenciador.hotel.domain.port.out.reserva.SaveReservaOutputPort;
import com.example.gerenciador.hotel.domain.usecase.reserva.BuscarReservaPorIdUseCase;
import com.example.gerenciador.hotel.domain.usecase.reserva.BuscarReservasAgendadasEmUsoPorCpfUseCase;
import com.example.gerenciador.hotel.domain.usecase.reserva.BuscarReservasFinalizadasCanceladasPorCpfUseCase;
import com.example.gerenciador.hotel.domain.usecase.reserva.BuscarReservasPorHospedeEStatusUseCase;
import com.example.gerenciador.hotel.domain.usecase.reserva.CriarReservaUseCase;
import com.example.gerenciador.hotel.domain.usecase.reserva.ModificarStatusReservaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaUseCaseConfig {

    @Bean
    public BuscarReservaPorIdUseCase buscarReservaPorIdUseCase(
            FindReservaByIdOutputPort findReservaByIdOutputPort
    ) {
        return new BuscarReservaPorIdUseCase(findReservaByIdOutputPort);
    }

    @Bean
    public BuscarReservasAgendadasEmUsoPorCpfUseCase buscarReservasAgendadasEmUsoPorCpfUseCase(
            FindAgendadasEmUsoPorCpfOutputPort findAgendadasEmUsoPorCpfOutputPort
    ) {
        return new BuscarReservasAgendadasEmUsoPorCpfUseCase(findAgendadasEmUsoPorCpfOutputPort);
    }

    @Bean
    public BuscarReservasFinalizadasCanceladasPorCpfUseCase buscarReservasFinalizadasCanceladasPorCpfUseCase(
            FindFinalizadasCanceladasPorCpfOutputPort findFinalizadasCanceladasPorCpfOutputPort
    ) {
        return new BuscarReservasFinalizadasCanceladasPorCpfUseCase(findFinalizadasCanceladasPorCpfOutputPort);
    }

    @Bean
    public BuscarReservasPorHospedeEStatusUseCase buscarReservasPorHospedeEStatusUseCase(
            FindByHospedeEStatusOutputPort findByHospedeEStatusOutputPort
    ) {
        return new BuscarReservasPorHospedeEStatusUseCase(findByHospedeEStatusOutputPort);
    }

    @Bean
    public CriarReservaUseCase criarReservaUseCase(
            FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort,
            QuartoEstaDisponivelParaDataOutputPort quartoEstaDisponivelParaDataOutputPort,
            FindHospedeByCpfOutputPort findHospedeByCpfOutputPort,
            SaveReservaOutputPort saveReservaOutputPort
    ) {
        return new CriarReservaUseCase(
                findQuartoByNumeroOutputPort,
                quartoEstaDisponivelParaDataOutputPort,
                findHospedeByCpfOutputPort,
                saveReservaOutputPort
        );
    }

    @Bean
    public ModificarStatusReservaUseCase modificarStatusReservaUseCase(
            BuscarReservaPorIdInputPort buscarReservaPorIdInputPort,
            SaveReservaOutputPort saveReservaOutputPort
    ) {
        return new ModificarStatusReservaUseCase(buscarReservaPorIdInputPort, saveReservaOutputPort);
    }
}

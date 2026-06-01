package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.out.reserva.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservaJpaAdapter implements
        SaveReservaOutputPort,
        FindReservaByIdOutputPort,
        QuartoEstaDisponivelParaDataOutputPort,
        FindByHospedeEStatusOutputPort,
        FindAgendadasEmUsoPorCpfOutputPort,
        FindFinalizadasCanceladasPorCpfOutputPort {

    private final ReservaJpaRepository jpaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public Reserva save(Reserva reserva) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(reserva)));
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean quartoEstaDisponivelParaData(LocalDateTime dataEntrada, LocalDateTime dataSaida, String numeroQuarto) {
        return jpaRepository.quartoEstaDisponivelParaData(dataEntrada, dataSaida, numeroQuarto);
    }

    @Override
    public Page<Reserva> findByHospedeEStatus(String cpf, StatusReserva status, Pageable pageable) {
        return jpaRepository.findByHospedeEStatus(cpf, status, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<Reserva> findAgendadasEmUsoPorCpf(String cpf, Pageable pageable) {
        return jpaRepository.findAgendadasEmUsoPorCpf(cpf, StatusReserva.AGENDADO, StatusReserva.EM_USO, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Reserva> findFinalizadasCanceladasPorCpf(String cpf, Pageable pageable) {
        return jpaRepository.findFinalizadasCanceladasPorCpf(cpf, StatusReserva.FINALIZADO, StatusReserva.CANCELADO, pageable)
                .map(mapper::toDomain);
    }
}

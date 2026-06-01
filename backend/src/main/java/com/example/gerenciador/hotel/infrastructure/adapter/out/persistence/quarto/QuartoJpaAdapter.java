package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.out.quarto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuartoJpaAdapter implements
        SaveQuartoOutputPort,
        FindQuartoByIdOutputPort,
        FindQuartoByNumeroOutputPort,
        ExistsQuartoByNumeroOutputPort,
        FindByTipoQuartoOutputPort,
        FindByTipoQuartoAndStatusOutputPort,
        FindByStatusOutputPort,
        FindOcupadosPorTipoOutputPort,
        FindByTipoQuartoAndStatusListOutputPort {

    private final QuartoJpaRepository jpaRepository;
    private final QuartoEntityMapper mapper;

    @Override
    public Quarto save(Quarto quarto) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(quarto)));
    }

    @Override
    public Optional<Quarto> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Quarto> findByNumero(String numero) {
        return jpaRepository.findByNumero(numero).map(mapper::toDomain);
    }

    @Override
    public boolean existsByNumero(String numero) {
        return jpaRepository.existsByNumero(numero);
    }

    @Override
    public Page<Quarto> findByTipoQuarto(TipoQuarto tipoQuarto, Pageable pageable) {
        return jpaRepository.findByTipoQuarto(tipoQuarto, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<Quarto> findByTipoQuartoAndStatus(TipoQuarto tipoQuarto, StatusQuarto status, Pageable pageable) {
        return jpaRepository.findByTipoQuartoAndStatusQuarto(tipoQuarto, status, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<Quarto> findByStatus(StatusQuarto status, Pageable pageable) {
        return jpaRepository.findByStatusQuarto(status, pageable).map(mapper::toDomain);
    }

    @Override
    public List<Quarto> findOcupadosPorTipo(TipoQuarto tipoQuarto, Date dataInicial, Date dataFinal, List<StatusReserva> statusReservas) {
        return jpaRepository.findOcupadosPorTipo(tipoQuarto, dataInicial, dataFinal, statusReservas)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Quarto> findByTipoQuartoAndStatusList(TipoQuarto tipoQuarto, StatusQuarto status) {
        return jpaRepository.findByTipoQuartoAndStatusQuarto(tipoQuarto, status)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}

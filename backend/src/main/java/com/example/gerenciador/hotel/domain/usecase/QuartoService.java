package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.*;
import com.example.gerenciador.hotel.domain.port.out.quarto.*;
import com.example.gerenciador.hotel.shared.exception.QuartoJaExisteException;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuartoService implements
        CriarQuartoInputPort,
        BuscarQuartoPorNumeroInputPort,
        EditarQuartoInputPort,
        ModificarStatusQuartoInputPort,
        BuscarQuartosPorTipoInputPort,
        BuscarQuartosPorTipoEStatusInputPort,
        BuscarQuartosPorStatusInputPort,
        BuscarQuartosDisponiveisPorTipoEDataInputPort {

    private final ExistsQuartoByNumeroOutputPort existsQuartoByNumeroOutputPort;
    private final SaveQuartoOutputPort saveQuartoOutputPort;
    private final FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort;
    private final FindByTipoQuartoOutputPort findByTipoQuartoOutputPort;
    private final FindByTipoQuartoAndStatusOutputPort findByTipoQuartoAndStatusOutputPort;
    private final FindByStatusOutputPort findByStatusOutputPort;
    private final FindOcupadosPorTipoOutputPort findOcupadosPorTipoOutputPort;
    private final FindByTipoQuartoAndStatusListOutputPort findByTipoQuartoAndStatusListOutputPort;

    @Override
    @Transactional
    public Quarto criarQuarto(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria, StatusQuarto statusQuarto) {
        if (existsQuartoByNumeroOutputPort.existsByNumero(numero)) {
            throw new QuartoJaExisteException("Quarto já cadastrado com esse número.");
        }
        Quarto quarto = Quarto.builder()
                .numero(numero)
                .tipoQuarto(tipoQuarto)
                .capacidade(capacidade)
                .precoDiaria(precoDiaria)
                .statusQuarto(statusQuarto)
                .build();
        return saveQuartoOutputPort.save(quarto);
    }

    @Override
    @Transactional(readOnly = true)
    public Quarto buscarQuartoPorNumero(String numero) {
        return findQuartoByNumeroOutputPort.findByNumero(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto com número " + numero + " não encontrado."));
    }

    @Override
    @Transactional
    public Quarto editarQuarto(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria, StatusQuarto statusQuarto) {
        Quarto quarto = buscarQuartoPorNumero(numero);
        quarto.setTipoQuarto(tipoQuarto);
        quarto.setCapacidade(capacidade);
        quarto.setPrecoDiaria(precoDiaria);
        quarto.setStatusQuarto(statusQuarto);
        return saveQuartoOutputPort.save(quarto);
    }

    @Override
    @Transactional
    public Quarto modificarStatusQuarto(String numero, StatusQuarto statusQuarto) {
        Quarto quarto = buscarQuartoPorNumero(numero);
        quarto.setStatusQuarto(statusQuarto);
        return saveQuartoOutputPort.save(quarto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorTipo(TipoQuarto tipoQuarto, Pageable pageable) {
        return findByTipoQuartoOutputPort.findByTipoQuarto(tipoQuarto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorTipoEStatus(TipoQuarto tipoQuarto, StatusQuarto statusQuarto, Pageable pageable) {
        return findByTipoQuartoAndStatusOutputPort.findByTipoQuartoAndStatus(tipoQuarto, statusQuarto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorStatus(StatusQuarto statusQuarto, Pageable pageable) {
        return findByStatusOutputPort.findByStatus(statusQuarto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Quarto> buscarQuartosDisponiveisPorTipoEData(TipoQuarto tipoQuarto, Date dataEntrada, Date dataSaida) {
        List<StatusReserva> statusOcupados = Arrays.asList(StatusReserva.AGENDADO, StatusReserva.EM_USO, StatusReserva.MANUTENCAO);

        List<Quarto> quartosOcupados = findOcupadosPorTipoOutputPort.findOcupadosPorTipo(tipoQuarto, dataEntrada, dataSaida, statusOcupados);
        List<Quarto> quartosDisponiveis = findByTipoQuartoAndStatusListOutputPort.findByTipoQuartoAndStatusList(tipoQuarto, StatusQuarto.DISPONIVEL);

        return quartosDisponiveis.stream()
                .filter(q -> quartosOcupados.stream().noneMatch(o -> o.getId().equals(q.getId())))
                .collect(Collectors.toList());
    }
}

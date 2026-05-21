package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.*;
import com.example.gerenciador.hotel.domain.port.out.QuartoRepositoryPort;
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
        CriarQuartoUseCase,
        BuscarQuartoPorNumeroUseCase,
        EditarQuartoUseCase,
        ModificarStatusQuartoUseCase,
        BuscarQuartosPorTipoUseCase,
        BuscarQuartosPorTipoEStatusUseCase,
        BuscarQuartosPorStatusUseCase,
        BuscarQuartosDisponiveisPorTipoEDataUseCase {

    private final QuartoRepositoryPort quartoRepository;

    @Override
    @Transactional
    public Quarto criarQuarto(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria, StatusQuarto statusQuarto) {
        if (quartoRepository.existsByNumero(numero)) {
            throw new QuartoJaExisteException("Quarto já cadastrado com esse número.");
        }
        Quarto quarto = Quarto.builder()
                .numero(numero)
                .tipoQuarto(tipoQuarto)
                .capacidade(capacidade)
                .precoDiaria(precoDiaria)
                .statusQuarto(statusQuarto)
                .build();
        return quartoRepository.save(quarto);
    }

    @Override
    @Transactional(readOnly = true)
    public Quarto buscarQuartoPorNumero(String numero) {
        return quartoRepository.findByNumero(numero)
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
        return quartoRepository.save(quarto);
    }

    @Override
    @Transactional
    public Quarto modificarStatusQuarto(String numero, StatusQuarto statusQuarto) {
        Quarto quarto = buscarQuartoPorNumero(numero);
        quarto.setStatusQuarto(statusQuarto);
        return quartoRepository.save(quarto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorTipo(TipoQuarto tipoQuarto, Pageable pageable) {
        return quartoRepository.findByTipoQuarto(tipoQuarto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorTipoEStatus(TipoQuarto tipoQuarto, StatusQuarto statusQuarto, Pageable pageable) {
        return quartoRepository.findByTipoQuartoAndStatus(tipoQuarto, statusQuarto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorStatus(StatusQuarto statusQuarto, Pageable pageable) {
        return quartoRepository.findByStatus(statusQuarto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Quarto> buscarQuartosDisponiveisPorTipoEData(TipoQuarto tipoQuarto, Date dataEntrada, Date dataSaida) {
        List<StatusReserva> statusOcupados = Arrays.asList(StatusReserva.AGENDADO, StatusReserva.EM_USO, StatusReserva.MANUTENCAO);

        List<Quarto> quartosOcupados = quartoRepository.findOcupadosPorTipo(tipoQuarto, dataEntrada, dataSaida, statusOcupados);
        List<Quarto> quartosDisponiveis = quartoRepository.findByTipoQuartoAndStatusList(tipoQuarto, StatusQuarto.DISPONIVEL);

        return quartosDisponiveis.stream()
                .filter(q -> quartosOcupados.stream().noneMatch(o -> o.getId().equals(q.getId())))
                .collect(Collectors.toList());
    }
}

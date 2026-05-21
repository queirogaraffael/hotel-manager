package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartosDisponiveisPorTipoEDataInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByTipoQuartoAndStatusListOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindOcupadosPorTipoOutputPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarQuartosDisponiveisPorTipoEDataUseCase implements BuscarQuartosDisponiveisPorTipoEDataInputPort {

    private final FindOcupadosPorTipoOutputPort findOcupadosPorTipoOutputPort;
    private final FindByTipoQuartoAndStatusListOutputPort findByTipoQuartoAndStatusListOutputPort;

    public BuscarQuartosDisponiveisPorTipoEDataUseCase(
            FindOcupadosPorTipoOutputPort findOcupadosPorTipoOutputPort,
            FindByTipoQuartoAndStatusListOutputPort findByTipoQuartoAndStatusListOutputPort
    ) {
        this.findOcupadosPorTipoOutputPort = findOcupadosPorTipoOutputPort;
        this.findByTipoQuartoAndStatusListOutputPort = findByTipoQuartoAndStatusListOutputPort;
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

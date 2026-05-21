package com.example.gerenciador.hotel.domain.port.in.quarto;

import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import java.util.Date;
import java.util.List;

public interface BuscarQuartosDisponiveisPorTipoEDataInputPort {
    List<Quarto> buscarQuartosDisponiveisPorTipoEData(TipoQuarto tipoQuarto, Date dataEntrada, Date dataSaida);
}

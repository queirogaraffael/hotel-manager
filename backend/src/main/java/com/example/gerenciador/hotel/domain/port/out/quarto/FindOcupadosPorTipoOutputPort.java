package com.example.gerenciador.hotel.domain.port.out.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import java.util.Date;
import java.util.List;

public interface FindOcupadosPorTipoOutputPort {
    List<Quarto> findOcupadosPorTipo(TipoQuarto tipoQuarto, Date dataInicial, Date dataFinal, List<StatusReserva> statusReservas);
}

package com.example.gerenciador.hotel.domain.port.in.quarto;

import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarQuartosPorTipoInputPort {
    Page<Quarto> buscarQuartosPorTipo(TipoQuarto tipoQuarto, Pageable pageable);
}

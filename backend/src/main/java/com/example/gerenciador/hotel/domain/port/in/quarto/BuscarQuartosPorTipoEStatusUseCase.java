package com.example.gerenciador.hotel.domain.port.in.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarQuartosPorTipoEStatusUseCase {
    Page<Quarto> buscarQuartosPorTipoEStatus(TipoQuarto tipoQuarto, StatusQuarto statusQuarto, Pageable pageable);
}

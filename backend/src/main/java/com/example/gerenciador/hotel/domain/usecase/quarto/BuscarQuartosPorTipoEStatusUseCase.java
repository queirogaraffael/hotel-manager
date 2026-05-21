package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartosPorTipoEStatusInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByTipoQuartoAndStatusOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class BuscarQuartosPorTipoEStatusUseCase implements BuscarQuartosPorTipoEStatusInputPort {

    private final FindByTipoQuartoAndStatusOutputPort findByTipoQuartoAndStatusOutputPort;

    public BuscarQuartosPorTipoEStatusUseCase(FindByTipoQuartoAndStatusOutputPort findByTipoQuartoAndStatusOutputPort) {
        this.findByTipoQuartoAndStatusOutputPort = findByTipoQuartoAndStatusOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorTipoEStatus(TipoQuarto tipoQuarto, StatusQuarto statusQuarto, Pageable pageable) {
        return findByTipoQuartoAndStatusOutputPort.findByTipoQuartoAndStatus(tipoQuarto, statusQuarto, pageable);
    }
}

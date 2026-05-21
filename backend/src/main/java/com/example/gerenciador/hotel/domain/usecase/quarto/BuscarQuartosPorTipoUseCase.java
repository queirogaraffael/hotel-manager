package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartosPorTipoInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByTipoQuartoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarQuartosPorTipoUseCase implements BuscarQuartosPorTipoInputPort {

    private final FindByTipoQuartoOutputPort findByTipoQuartoOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorTipo(TipoQuarto tipoQuarto, Pageable pageable) {
        return findByTipoQuartoOutputPort.findByTipoQuarto(tipoQuarto, pageable);
    }
}

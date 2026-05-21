package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartosPorStatusInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByStatusOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarQuartosPorStatusUseCase implements BuscarQuartosPorStatusInputPort {

    private final FindByStatusOutputPort findByStatusOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Page<Quarto> buscarQuartosPorStatus(StatusQuarto statusQuarto, Pageable pageable) {
        return findByStatusOutputPort.findByStatus(statusQuarto, pageable);
    }
}

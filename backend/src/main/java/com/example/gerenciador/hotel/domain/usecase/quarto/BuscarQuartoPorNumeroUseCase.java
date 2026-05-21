package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartoPorNumeroInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindQuartoByNumeroOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BuscarQuartoPorNumeroUseCase implements BuscarQuartoPorNumeroInputPort {

    private final FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort;

    public BuscarQuartoPorNumeroUseCase(FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort) {
        this.findQuartoByNumeroOutputPort = findQuartoByNumeroOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Quarto buscarQuartoPorNumero(String numero) {
        return findQuartoByNumeroOutputPort.findByNumero(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto com número " + numero + " não encontrado."));
    }
}

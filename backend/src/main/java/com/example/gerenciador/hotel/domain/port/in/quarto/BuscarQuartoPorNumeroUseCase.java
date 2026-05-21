package com.example.gerenciador.hotel.domain.port.in.quarto;

import com.example.gerenciador.hotel.domain.model.Quarto;

public interface BuscarQuartoPorNumeroUseCase {
    Quarto buscarQuartoPorNumero(String numero);
}

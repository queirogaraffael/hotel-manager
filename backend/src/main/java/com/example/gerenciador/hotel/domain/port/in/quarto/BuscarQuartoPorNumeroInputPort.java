package com.example.gerenciador.hotel.domain.port.in.quarto;

import com.example.gerenciador.hotel.domain.model.Quarto;

public interface BuscarQuartoPorNumeroInputPort {
    Quarto buscarQuartoPorNumero(String numero);
}

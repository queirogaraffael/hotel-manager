package com.example.gerenciador.hotel.domain.port.out.quarto;

import com.example.gerenciador.hotel.domain.model.Quarto;
import java.util.Optional;

public interface FindQuartoByNumeroOutputPort {
    Optional<Quarto> findByNumero(String numero);
}

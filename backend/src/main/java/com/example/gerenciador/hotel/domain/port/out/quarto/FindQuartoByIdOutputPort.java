package com.example.gerenciador.hotel.domain.port.out.quarto;

import com.example.gerenciador.hotel.domain.model.Quarto;
import java.util.Optional;

public interface FindQuartoByIdOutputPort {
    Optional<Quarto> findById(Long id);
}

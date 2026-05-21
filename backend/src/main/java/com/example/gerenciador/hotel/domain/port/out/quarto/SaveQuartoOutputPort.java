package com.example.gerenciador.hotel.domain.port.out.quarto;

import com.example.gerenciador.hotel.domain.model.Quarto;

public interface SaveQuartoOutputPort {
    Quarto save(Quarto quarto);
}

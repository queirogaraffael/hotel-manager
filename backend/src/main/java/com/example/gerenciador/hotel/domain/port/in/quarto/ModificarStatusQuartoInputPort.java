package com.example.gerenciador.hotel.domain.port.in.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;

public interface ModificarStatusQuartoInputPort {
    Quarto modificarStatusQuarto(String numero, StatusQuarto statusQuarto);
}

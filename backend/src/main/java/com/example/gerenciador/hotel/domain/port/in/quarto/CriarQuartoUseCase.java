package com.example.gerenciador.hotel.domain.port.in.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;

public interface CriarQuartoUseCase {
    Quarto criarQuarto(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria, StatusQuarto statusQuarto);
}

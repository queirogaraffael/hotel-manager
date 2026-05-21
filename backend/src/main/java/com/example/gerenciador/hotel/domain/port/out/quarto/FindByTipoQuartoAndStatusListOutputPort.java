package com.example.gerenciador.hotel.domain.port.out.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import java.util.List;

public interface FindByTipoQuartoAndStatusListOutputPort {
    List<Quarto> findByTipoQuartoAndStatusList(TipoQuarto tipoQuarto, StatusQuarto status);
}

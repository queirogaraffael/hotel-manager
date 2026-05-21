package com.example.gerenciador.hotel.domain.port.out.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindByTipoQuartoAndStatusOutputPort {
    Page<Quarto> findByTipoQuartoAndStatus(TipoQuarto tipoQuarto, StatusQuarto status, Pageable pageable);
}

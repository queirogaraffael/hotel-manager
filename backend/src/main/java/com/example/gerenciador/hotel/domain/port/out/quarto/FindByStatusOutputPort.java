package com.example.gerenciador.hotel.domain.port.out.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindByStatusOutputPort {
    Page<Quarto> findByStatus(StatusQuarto status, Pageable pageable);
}

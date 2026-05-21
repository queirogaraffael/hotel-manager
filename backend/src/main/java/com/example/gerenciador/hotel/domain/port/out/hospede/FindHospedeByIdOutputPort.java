package com.example.gerenciador.hotel.domain.port.out.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;
import java.util.Optional;

public interface FindHospedeByIdOutputPort {
    Optional<Hospede> findById(Long id);
}

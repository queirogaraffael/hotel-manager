package com.example.gerenciador.hotel.domain.port.out.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;

public interface SaveHospedeOutputPort {
    Hospede save(Hospede hospede);
}

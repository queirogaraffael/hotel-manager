package com.example.gerenciador.hotel.domain.port.out.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import java.util.Optional;

public interface FindExtratoFuncionarioByIdOutputPort {
    Optional<ExtratoFuncionario> findById(Long id);
}

package com.example.gerenciador.hotel.domain.port.in.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscarExtratosPorCpfUseCase {
    Page<ExtratoFuncionario> buscarExtratosPorCpf(String cpf, Pageable pageable);
}

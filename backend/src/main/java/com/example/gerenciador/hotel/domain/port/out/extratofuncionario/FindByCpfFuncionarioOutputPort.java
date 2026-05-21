package com.example.gerenciador.hotel.domain.port.out.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindByCpfFuncionarioOutputPort {
    Page<ExtratoFuncionario> findByCpfFuncionario(String cpf, Pageable pageable);
}

package com.example.gerenciador.hotel.domain.port.out;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface ExtratoFuncionarioRepositoryPort {

    ExtratoFuncionario save(ExtratoFuncionario extrato);
    Optional<ExtratoFuncionario> findById(Long id);
    Page<ExtratoFuncionario> findByCpfFuncionario(String cpf, Pageable pageable);
    boolean existeExtratoDeFuncionarioParaMes(String funcionarioCpf, Date dataExtrato);
}

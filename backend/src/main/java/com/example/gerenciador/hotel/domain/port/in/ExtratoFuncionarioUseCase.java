package com.example.gerenciador.hotel.domain.port.in;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ExtratoFuncionarioUseCase {

    ExtratoFuncionario criarExtrato(String cpfFuncionario, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario);
    ExtratoFuncionario buscarExtratoPorId(Long id);
    Page<ExtratoFuncionario> buscarExtratosPorCpf(String cpf, Pageable pageable);
    ExtratoFuncionario editarExtrato(Long id, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario);
    void deletarExtrato(Long id);
}


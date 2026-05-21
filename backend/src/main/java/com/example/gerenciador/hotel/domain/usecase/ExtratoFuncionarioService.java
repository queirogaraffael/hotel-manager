package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.*;
import com.example.gerenciador.hotel.domain.port.out.ExtratoFuncionarioRepositoryPort;
import com.example.gerenciador.hotel.domain.port.out.FuncionarioRepositoryPort;
import com.example.gerenciador.hotel.shared.exception.ExtratoJaExisteParaMesReferenteException;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExtratoFuncionarioService implements
        CriarExtratoUseCase,
        BuscarExtratoPorIdUseCase,
        BuscarExtratosPorCpfUseCase,
        EditarExtratoUseCase,
        DeletarExtratoUseCase {

    private final ExtratoFuncionarioRepositoryPort extratoRepository;
    private final FuncionarioRepositoryPort funcionarioRepository;

    @Override
    @Transactional
    public ExtratoFuncionario criarExtrato(String cpfFuncionario, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario) {
        if (extratoRepository.existeExtratoDeFuncionarioParaMes(cpfFuncionario, dataExtrato)) {
            throw new ExtratoJaExisteParaMesReferenteException("Extrato já existe para o mês referente.");
        }

        Funcionario funcionario = funcionarioRepository.findComExtratoByCpf(cpfFuncionario)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário com CPF " + cpfFuncionario + " não encontrado."));

        ExtratoFuncionario extrato = ExtratoFuncionario.builder()
                .dataExtrato(dataExtrato)
                .horasTrabalhadas(horasTrabalhadas)
                .valorHora(valorHora)
                .salario(salario)
                .funcionario(funcionario)
                .build();

        return extratoRepository.save(extrato);
    }

    @Override
    @Transactional(readOnly = true)
    public ExtratoFuncionario buscarExtratoPorId(Long id) {
        return extratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Extrato com id " + id + " não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExtratoFuncionario> buscarExtratosPorCpf(String cpf, Pageable pageable) {
        return extratoRepository.findByCpfFuncionario(cpf, pageable);
    }

    @Override
    @Transactional
    public ExtratoFuncionario editarExtrato(Long id, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario) {
        ExtratoFuncionario extrato = buscarExtratoPorId(id);
        extrato.setDataExtrato(dataExtrato);
        extrato.setHorasTrabalhadas(horasTrabalhadas);
        extrato.setValorHora(valorHora);
        extrato.setSalario(salario);
        return extratoRepository.save(extrato);
    }

    @Override
    @Transactional
    public void deletarExtrato(Long id) {
        buscarExtratoPorId(id); // garante que existe antes de deletar
        extratoRepository.deleteById(id);
    }
}

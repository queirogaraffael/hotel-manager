package com.example.gerenciador.hotel.domain.usecase.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.CriarExtratoInputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.ExisteExtratoDeFuncionarioParaMesOutputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.SaveExtratoFuncionarioOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindComExtratoByCpfOutputPort;
import com.example.gerenciador.hotel.shared.exception.ExtratoJaExisteParaMesReferenteException;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class CriarExtratoUseCase implements CriarExtratoInputPort {

    private final ExisteExtratoDeFuncionarioParaMesOutputPort existeExtratoDeFuncionarioParaMesOutputPort;
    private final FindComExtratoByCpfOutputPort findComExtratoByCpfOutputPort;
    private final SaveExtratoFuncionarioOutputPort saveExtratoFuncionarioOutputPort;

    public CriarExtratoUseCase(
            ExisteExtratoDeFuncionarioParaMesOutputPort existeExtratoDeFuncionarioParaMesOutputPort,
            FindComExtratoByCpfOutputPort findComExtratoByCpfOutputPort,
            SaveExtratoFuncionarioOutputPort saveExtratoFuncionarioOutputPort
    ) {
        this.existeExtratoDeFuncionarioParaMesOutputPort = existeExtratoDeFuncionarioParaMesOutputPort;
        this.findComExtratoByCpfOutputPort = findComExtratoByCpfOutputPort;
        this.saveExtratoFuncionarioOutputPort = saveExtratoFuncionarioOutputPort;
    }

    @Override
    @Transactional
    public ExtratoFuncionario criarExtrato(String cpfFuncionario, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario) {
        if (existeExtratoDeFuncionarioParaMesOutputPort.existeExtratoDeFuncionarioParaMes(cpfFuncionario, dataExtrato)) {
            throw new ExtratoJaExisteParaMesReferenteException("Extrato já existe para o mês referente.");
        }

        Funcionario funcionario = findComExtratoByCpfOutputPort.findComExtratoByCpf(cpfFuncionario)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário com CPF " + cpfFuncionario + " não encontrado."));

        ExtratoFuncionario extrato = ExtratoFuncionario.builder()
                .dataExtrato(dataExtrato)
                .horasTrabalhadas(horasTrabalhadas)
                .valorHora(valorHora)
                .salario(salario)
                .funcionario(funcionario)
                .build();

        return saveExtratoFuncionarioOutputPort.save(extrato);
    }
}

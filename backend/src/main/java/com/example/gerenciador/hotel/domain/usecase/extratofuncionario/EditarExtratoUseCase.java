package com.example.gerenciador.hotel.domain.usecase.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.BuscarExtratoPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.EditarExtratoInputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.SaveExtratoFuncionarioOutputPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class EditarExtratoUseCase implements EditarExtratoInputPort {

    private final BuscarExtratoPorIdInputPort buscarExtratoPorIdInputPort;
    private final SaveExtratoFuncionarioOutputPort saveExtratoFuncionarioOutputPort;

    public EditarExtratoUseCase(
            BuscarExtratoPorIdInputPort buscarExtratoPorIdInputPort,
            SaveExtratoFuncionarioOutputPort saveExtratoFuncionarioOutputPort
    ) {
        this.buscarExtratoPorIdInputPort = buscarExtratoPorIdInputPort;
        this.saveExtratoFuncionarioOutputPort = saveExtratoFuncionarioOutputPort;
    }

    @Override
    @Transactional
    public ExtratoFuncionario editarExtrato(Long id, Date dataExtrato, double horasTrabalhadas, double valorHora, double salario) {
        ExtratoFuncionario extrato = buscarExtratoPorIdInputPort.buscarExtratoPorId(id);
        extrato.setDataExtrato(dataExtrato);
        extrato.setHorasTrabalhadas(horasTrabalhadas);
        extrato.setValorHora(valorHora);
        extrato.setSalario(salario);
        return saveExtratoFuncionarioOutputPort.save(extrato);
    }
}

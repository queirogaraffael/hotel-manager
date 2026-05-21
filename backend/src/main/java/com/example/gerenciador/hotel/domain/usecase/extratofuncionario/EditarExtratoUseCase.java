package com.example.gerenciador.hotel.domain.usecase.extratofuncionario;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.BuscarExtratoPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.EditarExtratoInputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.SaveExtratoFuncionarioOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EditarExtratoUseCase implements EditarExtratoInputPort {

    private final BuscarExtratoPorIdInputPort buscarExtratoPorIdInputPort;
    private final SaveExtratoFuncionarioOutputPort saveExtratoFuncionarioOutputPort;

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

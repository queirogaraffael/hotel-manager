package com.example.gerenciador.hotel.infrastructure.configs.usecase;

import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.BuscarExtratoPorIdInputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.DeleteExtratoFuncionarioByIdOutputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.ExisteExtratoDeFuncionarioParaMesOutputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.FindByCpfFuncionarioOutputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.FindExtratoFuncionarioByIdOutputPort;
import com.example.gerenciador.hotel.domain.port.out.extratofuncionario.SaveExtratoFuncionarioOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindComExtratoByCpfOutputPort;
import com.example.gerenciador.hotel.domain.usecase.extratofuncionario.BuscarExtratoPorIdUseCase;
import com.example.gerenciador.hotel.domain.usecase.extratofuncionario.BuscarExtratosPorCpfUseCase;
import com.example.gerenciador.hotel.domain.usecase.extratofuncionario.CriarExtratoUseCase;
import com.example.gerenciador.hotel.domain.usecase.extratofuncionario.DeletarExtratoUseCase;
import com.example.gerenciador.hotel.domain.usecase.extratofuncionario.EditarExtratoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtratoFuncionarioUseCaseConfig {

    @Bean
    public BuscarExtratoPorIdUseCase buscarExtratoPorIdUseCase(
            FindExtratoFuncionarioByIdOutputPort findExtratoFuncionarioByIdOutputPort
    ) {
        return new BuscarExtratoPorIdUseCase(findExtratoFuncionarioByIdOutputPort);
    }

    @Bean
    public BuscarExtratosPorCpfUseCase buscarExtratosPorCpfUseCase(
            FindByCpfFuncionarioOutputPort findByCpfFuncionarioOutputPort
    ) {
        return new BuscarExtratosPorCpfUseCase(findByCpfFuncionarioOutputPort);
    }

    @Bean
    public CriarExtratoUseCase criarExtratoUseCase(
            ExisteExtratoDeFuncionarioParaMesOutputPort existeExtratoDeFuncionarioParaMesOutputPort,
            FindComExtratoByCpfOutputPort findComExtratoByCpfOutputPort,
            SaveExtratoFuncionarioOutputPort saveExtratoFuncionarioOutputPort
    ) {
        return new CriarExtratoUseCase(
                existeExtratoDeFuncionarioParaMesOutputPort,
                findComExtratoByCpfOutputPort,
                saveExtratoFuncionarioOutputPort
        );
    }

    @Bean
    public DeletarExtratoUseCase deletarExtratoUseCase(
            BuscarExtratoPorIdInputPort buscarExtratoPorIdInputPort,
            DeleteExtratoFuncionarioByIdOutputPort deleteExtratoFuncionarioByIdOutputPort
    ) {
        return new DeletarExtratoUseCase(buscarExtratoPorIdInputPort, deleteExtratoFuncionarioByIdOutputPort);
    }

    @Bean
    public EditarExtratoUseCase editarExtratoUseCase(
            BuscarExtratoPorIdInputPort buscarExtratoPorIdInputPort,
            SaveExtratoFuncionarioOutputPort saveExtratoFuncionarioOutputPort
    ) {
        return new EditarExtratoUseCase(buscarExtratoPorIdInputPort, saveExtratoFuncionarioOutputPort);
    }
}

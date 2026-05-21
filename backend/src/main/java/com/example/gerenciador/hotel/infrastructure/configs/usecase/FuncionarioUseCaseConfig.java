package com.example.gerenciador.hotel.infrastructure.configs.usecase;

import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoFuncionarioByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindFuncionarioByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindFuncionarioByNomeOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.SaveFuncionarioOutputPort;
import com.example.gerenciador.hotel.domain.usecase.funcionario.BuscarEnderecoFuncionarioPorCpfUseCase;
import com.example.gerenciador.hotel.domain.usecase.funcionario.BuscarFuncionarioPorCpfUseCase;
import com.example.gerenciador.hotel.domain.usecase.funcionario.BuscarFuncionariosPorNomeUseCase;
import com.example.gerenciador.hotel.domain.usecase.funcionario.CriarEnderecoParaFuncionarioUseCase;
import com.example.gerenciador.hotel.domain.usecase.funcionario.CriarFuncionarioUseCase;
import com.example.gerenciador.hotel.domain.usecase.funcionario.EditarFuncionarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuncionarioUseCaseConfig {

    @Bean
    public BuscarFuncionarioPorCpfUseCase buscarFuncionarioPorCpfUseCase(
            FindFuncionarioByCpfOutputPort findFuncionarioByCpfOutputPort
    ) {
        return new BuscarFuncionarioPorCpfUseCase(findFuncionarioByCpfOutputPort);
    }

    @Bean
    public BuscarEnderecoFuncionarioPorCpfUseCase buscarEnderecoFuncionarioPorCpfUseCase(
            GetEnderecoFuncionarioByCpfOutputPort getEnderecoFuncionarioByCpfOutputPort
    ) {
        return new BuscarEnderecoFuncionarioPorCpfUseCase(getEnderecoFuncionarioByCpfOutputPort);
    }

    @Bean
    public BuscarFuncionariosPorNomeUseCase buscarFuncionariosPorNomeUseCase(
            FindFuncionarioByNomeOutputPort findFuncionarioByNomeOutputPort
    ) {
        return new BuscarFuncionariosPorNomeUseCase(findFuncionarioByNomeOutputPort);
    }

    @Bean
    public CriarEnderecoParaFuncionarioUseCase criarEnderecoParaFuncionarioUseCase(
            BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort,
            SaveEnderecoOutputPort saveEnderecoOutputPort,
            SaveFuncionarioOutputPort saveFuncionarioOutputPort
    ) {
        return new CriarEnderecoParaFuncionarioUseCase(
                buscarFuncionarioPorCpfInputPort,
                saveEnderecoOutputPort,
                saveFuncionarioOutputPort
        );
    }

    @Bean
    public CriarFuncionarioUseCase criarFuncionarioUseCase(
            BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort
    ) {
        return new CriarFuncionarioUseCase(buscarFuncionarioPorCpfInputPort);
    }

    @Bean
    public EditarFuncionarioUseCase editarFuncionarioUseCase(
            BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort,
            SaveFuncionarioOutputPort saveFuncionarioOutputPort
    ) {
        return new EditarFuncionarioUseCase(buscarFuncionarioPorCpfInputPort, saveFuncionarioOutputPort);
    }
}

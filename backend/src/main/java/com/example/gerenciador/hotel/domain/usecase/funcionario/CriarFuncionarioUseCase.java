package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.in.funcionario.CriarFuncionarioInputPort;
import org.springframework.transaction.annotation.Transactional;

public class CriarFuncionarioUseCase implements CriarFuncionarioInputPort {

    private final BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort;

    public CriarFuncionarioUseCase(BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort) {
        this.buscarFuncionarioPorCpfInputPort = buscarFuncionarioPorCpfInputPort;
    }

    @Override
    @Transactional
    public Funcionario criarFuncionario(String cpf, String nome, String cargo, Turno turno) {
        // A criação do Funcionario passa pelo User — orquestrada pelo UserService
        return buscarFuncionarioPorCpfInputPort.buscarFuncionarioPorCpf(cpf);
    }
}

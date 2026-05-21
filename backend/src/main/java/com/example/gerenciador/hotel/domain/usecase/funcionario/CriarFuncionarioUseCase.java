package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.in.funcionario.CriarFuncionarioInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarFuncionarioUseCase implements CriarFuncionarioInputPort {

    private final BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort;

    @Override
    @Transactional
    public Funcionario criarFuncionario(String cpf, String nome, String cargo, Turno turno) {
        // A criação do Funcionario passa pelo User — orquestrada pelo UserService
        return buscarFuncionarioPorCpfInputPort.buscarFuncionarioPorCpf(cpf);
    }
}

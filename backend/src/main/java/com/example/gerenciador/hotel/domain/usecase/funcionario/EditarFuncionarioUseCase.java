package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.in.funcionario.EditarFuncionarioInputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.SaveFuncionarioOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditarFuncionarioUseCase implements EditarFuncionarioInputPort {

    private final BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort;
    private final SaveFuncionarioOutputPort saveFuncionarioOutputPort;

    @Override
    @Transactional
    public Funcionario editarFuncionario(String cpf, String nome, String cargo, Turno turno) {
        Funcionario funcionario = buscarFuncionarioPorCpfInputPort.buscarFuncionarioPorCpf(cpf);
        funcionario.setCargo(cargo);
        funcionario.setTurno(turno);
        return saveFuncionarioOutputPort.save(funcionario);
    }
}

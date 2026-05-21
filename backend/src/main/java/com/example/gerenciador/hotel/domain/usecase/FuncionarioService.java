package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.*;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoFuncionarioByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindFuncionarioByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.FindFuncionarioByNomeOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.SaveFuncionarioOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements
        CriarFuncionarioInputPort,
        BuscarFuncionarioPorCpfInputPort,
        EditarFuncionarioInputPort,
        BuscarFuncionariosPorNomeInputPort,
        CriarEnderecoParaFuncionarioUseCase,
        BuscarEnderecoFuncionarioPorCpfInputPort {

    private final FindFuncionarioByCpfOutputPort findFuncionarioByCpfOutputPort;
    private final SaveFuncionarioOutputPort saveFuncionarioOutputPort;
    private final FindFuncionarioByNomeOutputPort findFuncionarioByNomeOutputPort;
    private final SaveEnderecoOutputPort saveEnderecoOutputPort;
    private final GetEnderecoFuncionarioByCpfOutputPort getEnderecoFuncionarioByCpfOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        return findFuncionarioByCpfOutputPort.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário com CPF " + cpf + " não encontrado."));
    }

    @Override
    @Transactional
    public Funcionario criarFuncionario(String cpf, String nome, String cargo, Turno turno) {
        // A criação do Funcionario passa pelo User — orquestrada pelo UserService
        return buscarFuncionarioPorCpf(cpf);
    }

    @Override
    @Transactional
    public Funcionario editarFuncionario(String cpf, String nome, String cargo, Turno turno) {
        Funcionario funcionario = buscarFuncionarioPorCpf(cpf);
        funcionario.setCargo(cargo);
        funcionario.setTurno(turno);
        return saveFuncionarioOutputPort.save(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Funcionario> buscarFuncionariosPorNome(String nome, Pageable pageable) {
        return findFuncionarioByNomeOutputPort.findByNome(nome, pageable);
    }

    @Override
    @Transactional
    public Endereco criarEnderecoParaFuncionario(String cpf, Endereco endereco) {
        Funcionario funcionario = buscarFuncionarioPorCpf(cpf);
        Endereco enderecoSalvo = saveEnderecoOutputPort.save(endereco);
        funcionario.getUser().setEndereco(enderecoSalvo);
        saveFuncionarioOutputPort.save(funcionario);
        return enderecoSalvo;
    }

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoFuncionarioPorCpf(String cpf) {
        return getEnderecoFuncionarioByCpfOutputPort.getEnderecoFuncionarioByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário sem endereço cadastrado."));
    }
}

package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.*;
import com.example.gerenciador.hotel.domain.port.out.EnderecoRepositoryPort;
import com.example.gerenciador.hotel.domain.port.out.FuncionarioRepositoryPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements
        CriarFuncionarioUseCase,
        BuscarFuncionarioPorCpfUseCase,
        EditarFuncionarioUseCase,
        BuscarFuncionariosPorNomeUseCase,
        CriarEnderecoParaFuncionarioUseCase,
        BuscarEnderecoFuncionarioPorCpfUseCase {

    private final FuncionarioRepositoryPort funcionarioRepository;
    private final EnderecoRepositoryPort enderecoRepository;

    @Override
    @Transactional(readOnly = true)
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
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
        return funcionarioRepository.save(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Funcionario> buscarFuncionariosPorNome(String nome, Pageable pageable) {
        return funcionarioRepository.findByNome(nome, pageable);
    }

    @Override
    @Transactional
    public Endereco criarEnderecoParaFuncionario(String cpf, Endereco endereco) {
        Funcionario funcionario = buscarFuncionarioPorCpf(cpf);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        funcionario.getUser().setEndereco(enderecoSalvo);
        funcionarioRepository.save(funcionario);
        return enderecoSalvo;
    }

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoFuncionarioPorCpf(String cpf) {
        return enderecoRepository.getEnderecoFuncionarioByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário sem endereço cadastrado."));
    }
}

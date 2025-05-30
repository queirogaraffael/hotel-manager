package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.domain.repositories.EnderecoRepository;
import com.example.gerenciador.hotel.domain.repositories.FuncionarioRepository;
import com.example.gerenciador.hotel.shared.dtos.funcionario.FuncionarioRequestDTO;
import com.example.gerenciador.hotel.shared.dtos.funcionario.FuncionarioResponseDTO;
import com.example.gerenciador.hotel.shared.dtos.funcionario.FuncionarioUpdateDTO;
import com.example.gerenciador.hotel.domain.entities.Endereco;
import com.example.gerenciador.hotel.domain.entities.Funcionario;


import com.example.gerenciador.hotel.shared.exceptions.FuncionarioJaExisteException;
import com.example.gerenciador.hotel.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FuncionarioService {


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Transactional
    public FuncionarioRequestDTO criarFuncionario(FuncionarioRequestDTO funcionarioDTO) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByCpf(funcionarioDTO.cpf());

        if (funcionarioOptional.isPresent()) {
            throw new FuncionarioJaExisteException("Funcionario com este CPF já existe!");
        }

        Funcionario funcionario = new Funcionario();

        funcionario.setCpf(funcionarioDTO.cpf());
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setDataNascimento(funcionarioDTO.dataNascimento());
        funcionario.setNumeroTelefone(funcionarioDTO.numeroTelefone());
        funcionario.setCargo(funcionarioDTO.cargo());
        funcionario.setTurno(funcionarioDTO.turno());

        funcionarioRepository.save(funcionario);

        return funcionarioDTO;

    }


    @Transactional
    public Endereco criaEnderecoParaFuncionario(String cpf, Endereco endereco) {

        Funcionario funcionario = funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Funcionario com o CPF " + cpf + " não encontrado."));

        funcionario.setEnderecoHospede(endereco);

        funcionarioRepository.save(funcionario);

        return endereco;
    }


    @Transactional(readOnly = true)
    public Funcionario getFuncionarioByCPF(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário com o CPF " + cpf + " não encontrado."));
    }


    @Transactional(readOnly = true)
    public Page<FuncionarioResponseDTO> getFuncionariosDTOPorNomePaginados(String nome, Pageable pageable) {
        return funcionarioRepository.findFuncionariosDTOByNamePageados(nome, pageable);
    }


    @Transactional
    public FuncionarioUpdateDTO editaFuncionarioByCPF(String cpf, FuncionarioUpdateDTO funcionarioModificado) {
        Funcionario funcionario = getFuncionarioByCPF(cpf);

        funcionario.setNome(funcionarioModificado.nome());
        funcionario.setDataNascimento(funcionarioModificado.dataNascimento());
        funcionario.setNumeroTelefone(funcionarioModificado.numeroTelefone());
        funcionario.setCargo(funcionarioModificado.cargo());
        funcionario.setTurno(funcionarioModificado.turno());

        funcionarioRepository.save(funcionario);

        return funcionarioModificado;

    }


    @Transactional(readOnly = true)
    public Endereco getEnderecoFuncionarioByCPF(String cpf) {
        return enderecoRepository.getEnderecoFuncionarioByCPF(cpf).orElseThrow(() -> new ResourceNotFoundException("Funcionario sem endereço cadastrado"));
    }

}

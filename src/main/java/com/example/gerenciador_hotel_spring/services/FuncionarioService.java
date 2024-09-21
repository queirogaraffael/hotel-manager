package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.dtos.FuncionarioDTO;
import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Funcionario;
import com.example.gerenciador_hotel_spring.exceptions.FuncionarioJaExisteException;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.EnderecoRepository;
import com.example.gerenciador_hotel_spring.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FuncionarioService {


    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;


    @Transactional
    public Funcionario criarFuncionario(Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByCpf(funcionario.getCpf());

        if (funcionarioOptional.isPresent()) {
            throw new FuncionarioJaExisteException("Funcionario com este CPF já existe!");
        }

        return funcionarioRepository.save(funcionario);

    }


    @Transactional
    public Endereco criaEnderecoParaFuncionario(String cpf, Endereco endereco) {

        Funcionario funcionario = funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Funcionario com o CPF " + cpf + " não encontrado."));

        funcionario.setEndereco(endereco);

        funcionarioRepository.save(funcionario);

        return endereco;
    }



    @Transactional(readOnly = true)
    public Funcionario getFuncionarioByCPF(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário com o CPF " + cpf + " não encontrado."));
    }


    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> getFuncionariosDTOPorNomePaginados(String nome, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return funcionarioRepository.findFuncionariosDTOByNamePageados(nome, pageable);
    }


    @Transactional
    public Funcionario editaFuncionarioByCPF(String cpf, Funcionario funcionarioModificado) {
        Funcionario funcionario = getFuncionarioByCPF(cpf);

        funcionario.setNome(funcionarioModificado.getNome());
        funcionario.setDataNascimento(funcionarioModificado.getDataNascimento());
        funcionario.setNumeroTelefone(funcionarioModificado.getNumeroTelefone());
        funcionario.setCargo(funcionarioModificado.getCargo());
        funcionario.setTurno(funcionarioModificado.getTurno());

        return funcionarioRepository.save(funcionario);

    }


    @Transactional(readOnly = true)
    public Endereco getEnderecoFuncionarioByCPF(String cpf){
        return enderecoRepository.getEnderecoFuncionarioByCPF(cpf).orElseThrow(()-> new ResourceNotFoundException("Funcionario sem endereço cadastrado"));
    }

}

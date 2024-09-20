package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Funcionario;
import com.example.gerenciador_hotel_spring.entities.Hospede;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.EnderecoRepository;
import com.example.gerenciador_hotel_spring.repositories.FuncionarioRepository;
import com.example.gerenciador_hotel_spring.repositories.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    HospedeRepository hospedeRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Transactional
    public Endereco criaEnderecoParaHospede(String cpf, Endereco endereco) {

        Hospede hospede = hospedeRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Hospede com o CPF " + cpf + " não encontrado."));

        hospede.setEndereco(endereco);

        hospedeRepository.save(hospede);

        return endereco;
    }


    @Transactional
    public Endereco criaEnderecoParaFuncionario(String cpf, Endereco endereco) {

        Funcionario funcionario = funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Funcionario com o CPF " + cpf + " não encontrado."));

        funcionario.setEndereco(endereco);

        funcionarioRepository.save(funcionario);

        return endereco;
    }


    @Transactional(readOnly = true)
    public Endereco getEnderecoHospedeByCPF(String cpf) {
        return enderecoRepository.getEnderecoHospedeByCPF(cpf).orElseThrow(() -> new ResourceNotFoundException("Hospede sem endereço cadastrado."));
    }

    @Transactional
    public Endereco modificaEnderecoById(Long id, Endereco enderecoModificado) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereco com este id " + id + "não encontrado para modificação."));

        endereco.setRua(enderecoModificado.getRua());
        endereco.setNumero(enderecoModificado.getNumero());
        endereco.setCidade(enderecoModificado.getCidade());
        endereco.setBairro(enderecoModificado.getBairro());
        endereco.setEstado(enderecoModificado.getEstado());

        return enderecoRepository.save(endereco);

    }

    @Transactional
    public void deletaEnderecoById(Long id) {
        enderecoRepository.deleteById(id);
    }

}

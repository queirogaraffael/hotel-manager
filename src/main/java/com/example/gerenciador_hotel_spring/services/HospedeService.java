package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Hospede;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.EnderecoRepository;
import com.example.gerenciador_hotel_spring.repositories.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class HospedeService {
    
    @Autowired
    HospedeRepository hospedeRepository;

    @Autowired
    EnderecoRepository enderecoRepository;


    @Transactional
    public Endereco criaEnderecoParaHospede(String cpf, Endereco endereco) {

        Hospede hospede = hospedeRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Hospede com o CPF " + cpf + " não encontrado."));

        hospede.setEndereco(endereco);

        hospedeRepository.save(hospede);

        return endereco;
    }

    @Transactional(readOnly = true)
    public Endereco getEnderecoHospedeByCPF(String cpf) {
        return enderecoRepository.getEnderecoHospedeByCPF(cpf).orElseThrow(() -> new ResourceNotFoundException("Hospede sem endereço cadastrado."));
    }
}

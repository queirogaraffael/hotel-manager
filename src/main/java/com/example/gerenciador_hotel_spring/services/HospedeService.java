package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Hospede;
import com.example.gerenciador_hotel_spring.exceptions.HospedeJaExisteException;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.EnderecoRepository;
import com.example.gerenciador_hotel_spring.repositories.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Transactional
    public Hospede criarHospede(Hospede hospede) {
        Optional<Hospede> hospedeOptional = hospedeRepository.findByCpf(hospede.getCpf());

        if (hospedeOptional.isPresent()) {
            throw new HospedeJaExisteException("Hóspede com este CPF já existe!");
        }

        return hospedeRepository.save(hospede);

    }


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


    @Transactional(readOnly = true)
    public Hospede getHospedeByCPF(String cpf) {
        return hospedeRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com o CPF " + cpf + " não encontrado."));
    }


    @Transactional
    public Hospede editaHospedeByCPF(String cpf, Hospede hospedeModificado) {

        Hospede hospede = getHospedeByCPF(cpf);

        hospede.setNome(hospedeModificado.getNome());
        hospede.setDataNascimento(hospedeModificado.getDataNascimento());
        hospede.setNumeroTelefone(hospedeModificado.getNumeroTelefone());

        return hospedeRepository.save(hospede);

    }


}

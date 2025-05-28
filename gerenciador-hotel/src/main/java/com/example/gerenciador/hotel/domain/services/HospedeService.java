package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.domain.entities.Endereco;
import com.example.gerenciador.hotel.domain.entities.Hospede;
import com.example.gerenciador.hotel.domain.repositories.EnderecoRepository;
import com.example.gerenciador.hotel.domain.repositories.HospedeRepository;
import com.example.gerenciador.hotel.shared.dtos.hospede.HospedeCreateDTO;
import com.example.gerenciador.hotel.shared.dtos.hospede.HospedeUpdateDTO;
import com.example.gerenciador.hotel.shared.exceptions.HospedeJaExisteException;
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
    public HospedeCreateDTO criarHospede(HospedeCreateDTO hospedeCreateDTO) {
        Optional<Hospede> hospedeOptional = hospedeRepository.findByCpf(hospedeCreateDTO.cpf());

        if (hospedeOptional.isPresent()) {
            throw new HospedeJaExisteException("Hóspede com este CPF já existe!");
        }

        Hospede hospede = new Hospede();

        hospede.setCpf(hospedeCreateDTO.cpf());
        hospede.setNome(hospedeCreateDTO.nome());
        hospede.setDataNascimento(hospedeCreateDTO.dataNascimento());
        hospede.setNumeroTelefone(hospedeCreateDTO.numeroTelefone());

        hospedeRepository.save(hospede);

        return hospedeCreateDTO;

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
    public HospedeUpdateDTO editaHospedeByCPF(String cpf, HospedeUpdateDTO hospedeModificado) {

        Hospede hospede = getHospedeByCPF(cpf);

        hospede.setNome(hospedeModificado.nome());
        hospede.setDataNascimento(hospedeModificado.dataNascimento());
        hospede.setNumeroTelefone(hospedeModificado.numeroTelefone());

        hospedeRepository.save(hospede);

        return hospedeModificado;

    }


}

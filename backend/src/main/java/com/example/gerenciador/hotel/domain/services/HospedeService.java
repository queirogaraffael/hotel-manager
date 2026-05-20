package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.HospedeUseCase;
import com.example.gerenciador.hotel.domain.port.out.EnderecoRepositoryPort;
import com.example.gerenciador.hotel.domain.port.out.HospedeRepositoryPort;
import com.example.gerenciador.hotel.shared.exceptions.HospedeJaExisteException;
import com.example.gerenciador.hotel.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospedeService implements HospedeUseCase {

    private final HospedeRepositoryPort hospedeRepository;
    private final EnderecoRepositoryPort enderecoRepository;

    @Override
    @Transactional
    public Hospede criarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone) {
        if (hospedeRepository.findByCpf(cpf).isPresent()) {
            throw new HospedeJaExisteException("Hóspede com este CPF já existe!");
        }
        // A criação do Hospede passa pelo User — será orquestrada pelo UserService na Fase 4
        // Por hora, busca o hospede existente associado ao User já criado
        return hospedeRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com CPF " + cpf + " não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public Hospede buscarHospedePorCpf(String cpf) {
        return hospedeRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com CPF " + cpf + " não encontrado."));
    }

    @Override
    @Transactional
    public Hospede editarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone) {
        Hospede hospede = buscarHospedePorCpf(cpf);
        // dados pessoais ficam em User; o serviço de User é responsável por isso
        return hospedeRepository.save(hospede);
    }

    @Override
    @Transactional
    public Endereco criarEnderecoParaHospede(String cpf, Endereco endereco) {
        Hospede hospede = buscarHospedePorCpf(cpf);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        hospede.getUser().setEndereco(enderecoSalvo);
        hospedeRepository.save(hospede);
        return enderecoSalvo;
    }

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoHospedePorCpf(String cpf) {
        return enderecoRepository.getEnderecoHospedeByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede sem endereço cadastrado."));
    }
}

package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.hospede.*;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.SaveHospedeOutputPort;
import com.example.gerenciador.hotel.shared.exception.HospedeJaExisteException;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospedeService implements
        CriarHospedeInputPort,
        BuscarHospedePorCpfInputPort,
        EditarHospedeInputPort,
        CriarEnderecoParaHospedeInputPort,
        BuscarEnderecoHospedePorCpfInputPort {

    private final FindHospedeByCpfOutputPort findHospedeByCpfOutputPort;
    private final SaveHospedeOutputPort saveHospedeOutputPort;
    private final SaveEnderecoOutputPort saveEnderecoOutputPort;
    private final GetEnderecoHospedeByCpfOutputPort getEnderecoHospedeByCpfOutputPort;

    @Override
    @Transactional
    public Hospede criarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone) {
        if (findHospedeByCpfOutputPort.findByCpf(cpf).isPresent()) {
            throw new HospedeJaExisteException("Hóspede com este CPF já existe!");
        }
        // A criação do Hospede passa pelo User — será orquestrada pelo UserService na Fase 4
        // Por hora, busca o hospede existente associado ao User já criado
        return findHospedeByCpfOutputPort.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com CPF " + cpf + " não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public Hospede buscarHospedePorCpf(String cpf) {
        return findHospedeByCpfOutputPort.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com CPF " + cpf + " não encontrado."));
    }

    @Override
    @Transactional
    public Hospede editarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone) {
        Hospede hospede = buscarHospedePorCpf(cpf);
        // dados pessoais ficam em User; o serviço de User é responsável por isso
        return saveHospedeOutputPort.save(hospede);
    }

    @Override
    @Transactional
    public Endereco criarEnderecoParaHospede(String cpf, Endereco endereco) {
        Hospede hospede = buscarHospedePorCpf(cpf);
        Endereco enderecoSalvo = saveEnderecoOutputPort.save(endereco);
        hospede.getUser().setEndereco(enderecoSalvo);
        saveHospedeOutputPort.save(hospede);
        return enderecoSalvo;
    }

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoHospedePorCpf(String cpf) {
        return getEnderecoHospedeByCpfOutputPort.getEnderecoHospedeByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede sem endereço cadastrado."));
    }
}

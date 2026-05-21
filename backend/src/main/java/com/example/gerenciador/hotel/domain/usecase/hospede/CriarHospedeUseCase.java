package com.example.gerenciador.hotel.domain.usecase.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.in.hospede.CriarHospedeInputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.shared.exception.HospedeJaExisteException;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarHospedeUseCase implements CriarHospedeInputPort {

    private final FindHospedeByCpfOutputPort findHospedeByCpfOutputPort;

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
}

package com.example.gerenciador.hotel.domain.usecase.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.in.hospede.EditarHospedeInputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.SaveHospedeOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditarHospedeUseCase implements EditarHospedeInputPort {

    private final BuscarHospedePorCpfInputPort buscarHospedePorCpfInputPort;
    private final SaveHospedeOutputPort saveHospedeOutputPort;

    @Override
    @Transactional
    public Hospede editarHospede(String cpf, String nome, String dataNascimento, String numeroTelefone) {
        Hospede hospede = buscarHospedePorCpfInputPort.buscarHospedePorCpf(cpf);
        // dados pessoais ficam em User; o serviço de User é responsável por isso
        return saveHospedeOutputPort.save(hospede);
    }
}

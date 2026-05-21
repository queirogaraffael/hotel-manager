package com.example.gerenciador.hotel.domain.usecase.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.exception.HospedeNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BuscarHospedePorCpfUseCase implements BuscarHospedePorCpfInputPort {

    private final FindHospedeByCpfOutputPort findHospedeByCpfOutputPort;

    public BuscarHospedePorCpfUseCase(FindHospedeByCpfOutputPort findHospedeByCpfOutputPort) {
        this.findHospedeByCpfOutputPort = findHospedeByCpfOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Hospede buscarHospedePorCpf(String cpf) {
        return findHospedeByCpfOutputPort.findByCpf(cpf)
                .orElseThrow(() -> new HospedeNotFoundException("Hóspede com CPF " + cpf + " não encontrado."));
    }
}

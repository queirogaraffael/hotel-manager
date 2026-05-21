package com.example.gerenciador.hotel.domain.usecase.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarHospedePorCpfUseCase implements BuscarHospedePorCpfInputPort {

    private final FindHospedeByCpfOutputPort findHospedeByCpfOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Hospede buscarHospedePorCpf(String cpf) {
        return findHospedeByCpfOutputPort.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede com CPF " + cpf + " não encontrado."));
    }
}

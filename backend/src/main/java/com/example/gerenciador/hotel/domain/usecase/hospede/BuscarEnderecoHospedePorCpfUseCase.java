package com.example.gerenciador.hotel.domain.usecase.hospede;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarEnderecoHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarEnderecoHospedePorCpfUseCase implements BuscarEnderecoHospedePorCpfInputPort {

    private final GetEnderecoHospedeByCpfOutputPort getEnderecoHospedeByCpfOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoHospedePorCpf(String cpf) {
        return getEnderecoHospedeByCpfOutputPort.getEnderecoHospedeByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede sem endereço cadastrado."));
    }
}

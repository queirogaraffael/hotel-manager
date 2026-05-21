package com.example.gerenciador.hotel.domain.usecase.hospede;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarEnderecoHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.exception.EnderecoNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BuscarEnderecoHospedePorCpfUseCase implements BuscarEnderecoHospedePorCpfInputPort {

    private final GetEnderecoHospedeByCpfOutputPort getEnderecoHospedeByCpfOutputPort;

    public BuscarEnderecoHospedePorCpfUseCase(GetEnderecoHospedeByCpfOutputPort getEnderecoHospedeByCpfOutputPort) {
        this.getEnderecoHospedeByCpfOutputPort = getEnderecoHospedeByCpfOutputPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoHospedePorCpf(String cpf) {
        return getEnderecoHospedeByCpfOutputPort.getEnderecoHospedeByCpf(cpf)
                .orElseThrow(() -> new EnderecoNotFoundException("Hóspede sem endereço cadastrado."));
    }
}

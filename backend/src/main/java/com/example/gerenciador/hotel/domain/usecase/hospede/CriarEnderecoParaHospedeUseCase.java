package com.example.gerenciador.hotel.domain.usecase.hospede;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.in.hospede.CriarEnderecoParaHospedeInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.SaveHospedeOutputPort;
import org.springframework.transaction.annotation.Transactional;

public class CriarEnderecoParaHospedeUseCase implements CriarEnderecoParaHospedeInputPort {

    private final BuscarHospedePorCpfInputPort buscarHospedePorCpfInputPort;
    private final SaveEnderecoOutputPort saveEnderecoOutputPort;
    private final SaveHospedeOutputPort saveHospedeOutputPort;

    public CriarEnderecoParaHospedeUseCase(
            BuscarHospedePorCpfInputPort buscarHospedePorCpfInputPort,
            SaveEnderecoOutputPort saveEnderecoOutputPort,
            SaveHospedeOutputPort saveHospedeOutputPort
    ) {
        this.buscarHospedePorCpfInputPort = buscarHospedePorCpfInputPort;
        this.saveEnderecoOutputPort = saveEnderecoOutputPort;
        this.saveHospedeOutputPort = saveHospedeOutputPort;
    }

    @Override
    @Transactional
    public Endereco criarEnderecoParaHospede(String cpf, Endereco endereco) {
        Hospede hospede = buscarHospedePorCpfInputPort.buscarHospedePorCpf(cpf);
        Endereco enderecoSalvo = saveEnderecoOutputPort.save(endereco);
        hospede.getUser().setEndereco(enderecoSalvo);
        saveHospedeOutputPort.save(hospede);
        return enderecoSalvo;
    }
}

package com.example.gerenciador.hotel.infrastructure.configs.usecase;

import com.example.gerenciador.hotel.domain.port.in.hospede.BuscarHospedePorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.GetEnderecoHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.FindHospedeByCpfOutputPort;
import com.example.gerenciador.hotel.domain.port.out.hospede.SaveHospedeOutputPort;
import com.example.gerenciador.hotel.domain.usecase.hospede.BuscarEnderecoHospedePorCpfUseCase;
import com.example.gerenciador.hotel.domain.usecase.hospede.BuscarHospedePorCpfUseCase;
import com.example.gerenciador.hotel.domain.usecase.hospede.CriarEnderecoParaHospedeUseCase;
import com.example.gerenciador.hotel.domain.usecase.hospede.CriarHospedeUseCase;
import com.example.gerenciador.hotel.domain.usecase.hospede.EditarHospedeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HospedeUseCaseConfig {

    @Bean
    public BuscarHospedePorCpfUseCase buscarHospedePorCpfUseCase(
            FindHospedeByCpfOutputPort findHospedeByCpfOutputPort
    ) {
        return new BuscarHospedePorCpfUseCase(findHospedeByCpfOutputPort);
    }

    @Bean
    public BuscarEnderecoHospedePorCpfUseCase buscarEnderecoHospedePorCpfUseCase(
            GetEnderecoHospedeByCpfOutputPort getEnderecoHospedeByCpfOutputPort
    ) {
        return new BuscarEnderecoHospedePorCpfUseCase(getEnderecoHospedeByCpfOutputPort);
    }

    @Bean
    public CriarHospedeUseCase criarHospedeUseCase(
            FindHospedeByCpfOutputPort findHospedeByCpfOutputPort
    ) {
        return new CriarHospedeUseCase(findHospedeByCpfOutputPort);
    }

    @Bean
    public CriarEnderecoParaHospedeUseCase criarEnderecoParaHospedeUseCase(
            BuscarHospedePorCpfInputPort buscarHospedePorCpfInputPort,
            SaveEnderecoOutputPort saveEnderecoOutputPort,
            SaveHospedeOutputPort saveHospedeOutputPort
    ) {
        return new CriarEnderecoParaHospedeUseCase(
                buscarHospedePorCpfInputPort,
                saveEnderecoOutputPort,
                saveHospedeOutputPort
        );
    }

    @Bean
    public EditarHospedeUseCase editarHospedeUseCase(
            BuscarHospedePorCpfInputPort buscarHospedePorCpfInputPort,
            SaveHospedeOutputPort saveHospedeOutputPort
    ) {
        return new EditarHospedeUseCase(buscarHospedePorCpfInputPort, saveHospedeOutputPort);
    }
}

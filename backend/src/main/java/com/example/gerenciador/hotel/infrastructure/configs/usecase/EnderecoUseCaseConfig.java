package com.example.gerenciador.hotel.infrastructure.configs.usecase;

import com.example.gerenciador.hotel.domain.port.out.endereco.FindEnderecoByIdOutputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.usecase.endereco.ModificarEnderecoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoUseCaseConfig {

    @Bean
    public ModificarEnderecoUseCase modificarEnderecoUseCase(
            FindEnderecoByIdOutputPort findEnderecoByIdOutputPort,
            SaveEnderecoOutputPort saveEnderecoOutputPort
    ) {
        return new ModificarEnderecoUseCase(findEnderecoByIdOutputPort, saveEnderecoOutputPort);
    }
}

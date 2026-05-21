package com.example.gerenciador.hotel.infrastructure.configs.usecase;

import com.example.gerenciador.hotel.domain.port.out.user.FindUserByUsernameOutputPort;
import com.example.gerenciador.hotel.domain.port.out.user.SaveUserOutputPort;
import com.example.gerenciador.hotel.domain.usecase.user.AutenticarUseCase;
import com.example.gerenciador.hotel.domain.usecase.user.BuscarPorUsernameUseCase;
import com.example.gerenciador.hotel.domain.usecase.user.RegistrarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserUseCaseConfig {

    @Bean
    public BuscarPorUsernameUseCase buscarPorUsernameUseCase(
            FindUserByUsernameOutputPort findUserByUsernameOutputPort
    ) {
        return new BuscarPorUsernameUseCase(findUserByUsernameOutputPort);
    }

    @Bean
    public AutenticarUseCase autenticarUseCase(
            FindUserByUsernameOutputPort findUserByUsernameOutputPort
    ) {
        return new AutenticarUseCase(findUserByUsernameOutputPort);
    }

    @Bean
    public RegistrarUsuarioUseCase registrarUsuarioUseCase(
            SaveUserOutputPort saveUserOutputPort,
            PasswordEncoder passwordEncoder
    ) {
        return new RegistrarUsuarioUseCase(saveUserOutputPort, passwordEncoder);
    }
}

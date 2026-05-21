package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.enums.UserRole;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.*;
import com.example.gerenciador.hotel.domain.port.out.UserRepositoryPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService implements
        RegistrarUsuarioUseCase,
        AutenticarUseCase,
        BuscarPorUsernameUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registrarUsuario(UserRole role, String username, String password, String nome,
                                 String email, String telefone, String cpf, LocalDate dataNascimento,
                                 String cargo, Turno turno) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .cpf(cpf)
                .dataNascimento(dataNascimento)
                .userRole(role)
                .build();
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User autenticar(String username, String password) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário '" + username + "' não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public User buscarPorUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário '" + username + "' não encontrado."));
    }
}

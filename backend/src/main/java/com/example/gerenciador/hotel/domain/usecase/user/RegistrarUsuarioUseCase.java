package com.example.gerenciador.hotel.domain.usecase.user;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.enums.UserRole;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.RegistrarUsuarioInputPort;
import com.example.gerenciador.hotel.domain.port.out.user.SaveUserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RegistrarUsuarioUseCase implements RegistrarUsuarioInputPort {

    private final SaveUserOutputPort saveUserOutputPort;
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
        return saveUserOutputPort.save(user);
    }
}

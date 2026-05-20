package com.example.gerenciador.hotel.domain.port.in;

import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.enums.UserRole;
import com.example.gerenciador.hotel.domain.model.User;

import java.time.LocalDate;

public interface UserUseCase {

    User registrarUsuario(UserRole role, String username, String password, String nome,
                          String email, String telefone, String cpf, LocalDate dataNascimento,
                          String cargo, Turno turno);

    User autenticar(String username, String password);

    User buscarPorUsername(String username);
}


package com.example.gerenciador.hotel.shared.dtos.user;

import com.example.gerenciador.hotel.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String login;
    private String cpf;
    private LocalDate dataNascimento;
    private String nome;
    private String email;
    private String telefone;
    private Set<UserRole> roles;

    private Long hospedeId;
    private Long funcionarioId;

}
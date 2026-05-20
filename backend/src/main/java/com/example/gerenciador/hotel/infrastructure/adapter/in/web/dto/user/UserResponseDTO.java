package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user;

import com.example.gerenciador.hotel.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String cpf;
    private LocalDate dataNascimento;
    private String nome;
    private String email;
    private String telefone;
    private UserRole userRole;
}

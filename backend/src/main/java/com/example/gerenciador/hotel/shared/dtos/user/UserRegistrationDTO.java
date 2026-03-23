package com.example.gerenciador.hotel.shared.dtos.user;

import com.example.gerenciador.hotel.domain.enums.UserRole;
import com.example.gerenciador.hotel.shared.dtos.endereco.EnderecoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    @NotBlank(message = "O login é obrigatório")
    private String login;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
    private String cpf;

    private LocalDate dataNascimento;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    private String telefone;

    private Set<UserRole> roles = new HashSet<>();

    @NotNull(message = "Dados de endereço são obrigatórios")
    private EnderecoRequestDTO endereco;
}
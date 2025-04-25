package com.example.gerenciador.hotel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(unique = true, nullable = false)
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    private String nome;

    @Email
    private String email;

    private String telefone;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Hospede hospede;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Funcionario funcionario;

}

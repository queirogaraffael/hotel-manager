package com.example.gerenciador.hotel.factories;

import com.example.gerenciador.hotel.entities.Funcionario;
import com.example.gerenciador.hotel.entities.Hospede;
import com.example.gerenciador.hotel.entities.User;
import com.example.gerenciador.hotel.domain.enums.Turno;
import com.example.gerenciador.hotel.domain.enums.UserRole;
import com.example.gerenciador.hotel.shared.dtos.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.shared.mappers.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserFactory {

    private final EnderecoMapper enderecoMapper;

    @Autowired
    public UserFactory(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    public User createUser(UserRole role, String username, String password, String nome, String email,
                           String telefone, String cpf, LocalDate dataNascimento, EnderecoRequestDTO enderecoDTO,
                           String cargo, Turno turno) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNome(nome);
        user.setEmail(email);
        user.setTelefone(telefone);
        user.setCpf(cpf);
        user.setDataNascimento(dataNascimento);
        if (enderecoDTO != null) {
            user.setEndereco(enderecoMapper.toEntity(enderecoDTO));
        }
        user.setUserRole(role);

        switch (role) {
            case HOSPEDE:
                Hospede hospede = new Hospede();
                hospede.setUser(user);
                user.setHospede(hospede);
                break;
            case FUNCIONARIO:
                if (cargo == null || turno == null) {
                    throw new IllegalArgumentException("Funcionário precisa de cargo e turno.");
                }
                Funcionario funcionario = new Funcionario();
                funcionario.setUser(user);
                funcionario.setCargo(cargo);
                funcionario.setTurno(turno);
                user.setFuncionario(funcionario);
                break;
            case ADMIN:
                break;
            default:
                throw new IllegalArgumentException("Role de usuário não suportada: " + role);
        }

        return user;
    }

    public User createHospedeUser(String username, String password, String nome, String email, String telefone,
                                  String cpf, LocalDate dataNascimento, EnderecoRequestDTO endereco) {
        return createUser(UserRole.HOSPEDE, username, password, nome, email, telefone, cpf, dataNascimento, endereco, null, null);
    }

    public User createFuncionarioUser(String username, String password, String nome, String email, String telefone,
                                      String cpf, LocalDate dataNascimento, EnderecoRequestDTO endereco,
                                      String cargo, Turno turno) {
        return createUser(UserRole.FUNCIONARIO, username, password, nome, email, telefone, cpf, dataNascimento, endereco, cargo, turno);
    }

    public User createAdminUser(String username, String password, String nome, String email, String telefone,
                                String cpf, LocalDate dataNascimento, EnderecoRequestDTO endereco) {
        return createUser(UserRole.ADMIN, username, password, nome, email, telefone, cpf, dataNascimento, endereco, null, null);
    }
}
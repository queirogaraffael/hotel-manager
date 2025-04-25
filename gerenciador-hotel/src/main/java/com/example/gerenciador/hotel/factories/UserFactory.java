package com.example.gerenciador.hotel.factories;

import com.example.gerenciador.hotel.dtos.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.entities.Funcionario;
import com.example.gerenciador.hotel.entities.Hospede;
import com.example.gerenciador.hotel.entities.User;
import com.example.gerenciador.hotel.entities.UserRole;
import com.example.gerenciador.hotel.enums.Turno;
import com.example.gerenciador.hotel.mappers.EnderecoMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserFactory {

    private final EnderecoMapper enderecoMapper;

    public UserFactory(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    public User createUser(UserRole role, String login, String senha, String nome, String email, String telefone, String cpf, Date dataNascimento, EnderecoRequestDTO enderecoDTO, String cargo, Turno turno) {
        User user = new User();
        user.setLogin(login);
        user.setSenha(senha);
        user.setNome(nome);
        user.setEmail(email);
        user.setTelefone(telefone);
        user.setCpf(cpf);
        user.setDataNascimento(dataNascimento);
        user.setEndereco(enderecoMapper.toEntity(enderecoDTO));
        user.setRole(role);

        switch (role) {
            case ADMIN:
                break;

            case HOSPEDE:
                Hospede hospede = new Hospede();
                hospede.setUser(user);
                user.setHospede(hospede);
                break;

            case FUNCIONARIO:
                Funcionario funcionario = new Funcionario();
                funcionario.setUser(user);
                funcionario.setCargo(cargo);
                funcionario.setTurno(turno);
                user.setFuncionario(funcionario);
                break;

            default:
                throw new IllegalArgumentException("Role desconhecido: " + role);
        }

        return user;
    }

    public User createHospedeUser(String login, String senha, String nome, String email, String telefone, String cpf, Date dataNascimento, EnderecoRequestDTO endereco) {
        return createUser(UserRole.HOSPEDE, login, senha, nome, email, telefone, cpf, dataNascimento, endereco, null, null);
    }

    public User createFuncionarioUser(String login, String senha, String nome, String email, String telefone, String cpf, Date dataNascimento, EnderecoRequestDTO endereco, String cargo, Turno turno) {
        return createUser(UserRole.FUNCIONARIO, login, senha, nome, email, telefone, cpf, dataNascimento, endereco, cargo, turno);
    }

    public User createAdminUser(String login, String senha, String nome, String email, String telefone, String cpf, Date dataNascimento, EnderecoRequestDTO endereco) {
        return createUser(UserRole.ADMIN, login, senha, nome, email, telefone, cpf, dataNascimento, endereco, null, null);
    }
}


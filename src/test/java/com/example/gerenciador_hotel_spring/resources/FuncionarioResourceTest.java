package com.example.gerenciador_hotel_spring.resources;
import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Funcionario;
import com.example.gerenciador_hotel_spring.repositories.FuncionarioRepository;
import com.example.gerenciador_hotel_spring.services.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes de FuncionarioResource")
class FuncionarioResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        funcionarioRepository.deleteAll();
    }


    @Test
    @DisplayName("Teste para criar um funcionário.")
    void criaFuncionario_Sucesso() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("João Silva");
        funcionario.setDataNascimento(new Date());

        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João Silva"));
    }


    @Test
    @DisplayName("Teste para obter um funcionário pelo CPF.")
    void getFuncionarioByCpf_Sucesso() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("João Silva");
        funcionario.setDataNascimento(new Date());
        funcionarioRepository.save(funcionario);

        mockMvc.perform(get("/api/funcionarios/12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João Silva"));
    }


    @Test
    @DisplayName("Teste para atualizar um funcionário.")
    void atualizaFuncionario_Sucesso() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("João Silva");
        funcionario.setDataNascimento(new Date());
        funcionarioRepository.save(funcionario);

        Funcionario funcionarioAtualizado = new Funcionario();
        funcionarioAtualizado.setNome("Maria Silva");

        mockMvc.perform(put("/api/funcionarios/12345678901")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioAtualizado)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Maria Silva"));
    }


    @Test
    @DisplayName("Teste de conflito ao tentar criar um funcionário já existente.")
    void criaFuncionario_Conflito() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("João Silva");
        funcionarioRepository.save(funcionario);

        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    @Test
    @DisplayName("Teste para criar endereço de um funcionário com sucesso.")
    void criaEnderecoFuncionario_Sucesso() throws Exception {
        String cpf = "12345678901";
        Endereco endereco = new Endereco(null, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(cpf);
        funcionario.setNome("João Silva");
        funcionario.setDataNascimento(new Date());

        funcionarioRepository.save(funcionario);

        mockMvc.perform(post("/api/funcionarios/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value("Cidade X"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value("Bairro Y"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value("Estado Z"));
    }


    @Test
    @DisplayName("Teste para obter o endereço de um funcionário pelo CPF quando não encontrado.")
    void getEnderecoFuncionarioByCpf_NotFound() throws Exception {
        String cpf = "12345678901";

        mockMvc.perform(get("/api/funcionarios/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    @DisplayName("Teste para buscar funcionários por nome de forma paginada com sucesso")
    void buscaFuncionariosPorNomePaginado_Sucesso() throws Exception {
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setCpf("12345678901");
        funcionario1.setNome("João Silva");
        funcionarioRepository.save(funcionario1);

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setCpf("98765432109");
        funcionario2.setNome("Joana Souza");
        funcionarioRepository.save(funcionario2);

        mockMvc.perform(get("/api/funcionarios/nome")
                        .param("nome", "Jo")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(2)) // Verifica que existem 2 resultados na página
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nome").value("João Silva"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].nome").value("Joana Souza"));
    }

}

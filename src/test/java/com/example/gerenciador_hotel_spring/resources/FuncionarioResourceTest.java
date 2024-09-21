package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.dtos.FuncionarioDTO;
import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Funcionario;
import com.example.gerenciador_hotel_spring.exceptions.FuncionarioJaExisteException;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.services.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Funcionarios")
class FuncionarioResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FuncionarioService funcionarioService;

    @Test
    @DisplayName("Teste para criar um funcionário.")
    void criaFuncionario_Sucesso() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("João Silva");
        funcionario.setDataNascimento(new Date());
        funcionario.setCargo("Desenvolvedor");

        Mockito.when(funcionarioService.criarFuncionario(Mockito.any(Funcionario.class))).thenReturn(funcionario);

        mockMvc.perform(post("/funcionarios")
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

        Mockito.when(funcionarioService.getFuncionarioByCPF("12345678901")).thenReturn(funcionario);

        mockMvc.perform(get("/funcionarios/12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João Silva"));
    }


    @Test
    @DisplayName("Teste para buscar funcionários paginados por nome.")
    void buscaFuncionariosPorNome_Sucesso() throws Exception {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(1L, "João Silva", "12345678901");
        Page<FuncionarioDTO> page = new PageImpl<>(List.of(funcionarioDTO));

        Mockito.when(funcionarioService.getFuncionariosDTOPorNomePaginados(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(page);

        mockMvc.perform(get("/funcionarios/nome")
                        .param("nome", "João Silva")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nome").value("João Silva")); // Ajuste o valor esperado
    }


    @Test
    @DisplayName("Teste para atualizar um funcionário.")
    void atualizaFuncionario_Sucesso() throws Exception {
        Funcionario funcionarioAtualizado = new Funcionario();
        funcionarioAtualizado.setCpf("12345678901");
        funcionarioAtualizado.setNome("Maria Silva");

        Mockito.when(funcionarioService.editaFuncionarioByCPF(Mockito.eq("12345678901"), Mockito.any(Funcionario.class)))
                .thenReturn(funcionarioAtualizado);

        mockMvc.perform(put("/funcionarios/12345678901")
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

        Mockito.when(funcionarioService.criarFuncionario(Mockito.any(Funcionario.class)))
                .thenThrow(new FuncionarioJaExisteException("Funcionario com este CPF já existe!"));

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    @Test
    @DisplayName("Teste para criar endereço de funcionário.")
    void criaEnderecoFuncionario_EnderecoJaExiste() throws Exception {
        String cpf = "12345678901";
        Endereco endereco = new Endereco(null, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);

        Mockito.when(funcionarioService.getEnderecoFuncionarioByCPF(cpf)).thenReturn(endereco);

        mockMvc.perform(post("/funcionarios/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    @Test
    @DisplayName("Teste para criar endereço de funcionário com sucesso.")
    void criaEnderecoFuncionario_Sucesso() throws Exception {
        Endereco endereco = new Endereco(null, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);
        String cpf = "12345678901";

        Mockito.when(funcionarioService.getEnderecoFuncionarioByCPF(cpf)).thenReturn(null);
        Mockito.when(funcionarioService.criaEnderecoParaFuncionario(Mockito.eq(cpf), Mockito.any(Endereco.class)))
                .thenReturn(endereco);

        mockMvc.perform(post("/funcionarios/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua A"));
    }


    @Test
    @DisplayName("Teste para obter o endereço de um funcionário pelo CPF com sucesso.")
    void getEnderecoFuncionarioByCpf_Sucesso() throws Exception {
        Endereco endereco = new Endereco(1L, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);
        String cpf = "12345678901";

        Mockito.when(funcionarioService.getEnderecoFuncionarioByCPF(cpf)).thenReturn(endereco);

        mockMvc.perform(get("/funcionarios/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value("123"));
    }


    @Test
    @DisplayName("Teste para obter o endereço de um funcionário pelo CPF quando não encontrado.")
    void getEnderecoFuncionarioByCpf_NotFound() throws Exception {
        String cpf = "12345678901";

        Mockito.when(funcionarioService.getEnderecoFuncionarioByCPF(cpf))
                .thenThrow(new ResourceNotFoundException("Funcionario sem endereço cadastrado"));

        mockMvc.perform(get("/funcionarios/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}


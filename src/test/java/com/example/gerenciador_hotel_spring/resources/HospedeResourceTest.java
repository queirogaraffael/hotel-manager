package com.example.gerenciador_hotel_spring.resources;


import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Hospede;
import com.example.gerenciador_hotel_spring.exceptions.HospedeJaExisteException;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.services.HospedeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes de HospedeResource")
class HospedeResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HospedeService hospedeService;

    @Test
    @DisplayName("Teste para criar um hóspede.")
    void criaHospede_Sucesso() throws Exception {
        Hospede hospede = new Hospede();
        hospede.setCpf("12345678901");
        hospede.setNome("João Silva");
        hospede.setDataNascimento(new Date());

        Mockito.when(hospedeService.criarHospede(Mockito.any(Hospede.class))).thenReturn(hospede);

        mockMvc.perform(post("/api/hospedes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hospede)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João Silva"));
    }


    @Test
    @DisplayName("Teste para obter um hóspede pelo CPF.")
    void getHospedeByCpf_Sucesso() throws Exception {
        Hospede hospede = new Hospede();
        hospede.setCpf("12345678901");
        hospede.setNome("João Silva");

        Mockito.when(hospedeService.getHospedeByCPF("12345678901")).thenReturn(hospede);

        mockMvc.perform(get("/api/hospedes/12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João Silva"));
    }


    @Test
    @DisplayName("Teste para atualizar um hóspede.")
    void atualizaHospede_Sucesso() throws Exception {
        Hospede hospedeAtualizado = new Hospede();
        hospedeAtualizado.setCpf("12345678901");
        hospedeAtualizado.setNome("Maria Silva");

        Mockito.when(hospedeService.editaHospedeByCPF(Mockito.eq("12345678901"), Mockito.any(Hospede.class)))
                .thenReturn(hospedeAtualizado);

        mockMvc.perform(put("/api/hospedes/12345678901")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hospedeAtualizado)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Maria Silva"));
    }


    @Test
    @DisplayName("Teste de conflito ao tentar criar um hóspede já existente.")
    void criaHospede_Conflito() throws Exception {
        Hospede hospede = new Hospede();
        hospede.setCpf("12345678901");
        hospede.setNome("João Silva");

        Mockito.when(hospedeService.criarHospede(Mockito.any(Hospede.class)))
                .thenThrow(new HospedeJaExisteException("Hóspede com este CPF já existe!"));

        mockMvc.perform(post("/api/hospedes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hospede)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    @Test
    @DisplayName("Teste para criar endereço de hóspede.")
    void criaEnderecoHospede_EnderecoJaExiste() throws Exception {
        String cpf = "12345678901";
        Endereco endereco = new Endereco(null, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);

        Mockito.when(hospedeService.getEnderecoHospedeByCPF(cpf)).thenReturn(endereco);

        mockMvc.perform(post("/api/hospedes/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    @Test
    @DisplayName("Teste para criar endereço de hóspede com sucesso.")
    void criaEnderecoHospede_Sucesso() throws Exception {
        Endereco endereco = new Endereco(null, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);
        String cpf = "12345678901";

        Mockito.when(hospedeService.getEnderecoHospedeByCPF(cpf)).thenReturn(null);
        Mockito.when(hospedeService.criaEnderecoParaHospede(Mockito.eq(cpf), Mockito.any(Endereco.class)))
                .thenReturn(endereco);

        mockMvc.perform(post("/api/hospedes/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua A"));
    }


    @Test
    @DisplayName("Teste para obter o endereço de um hóspede pelo CPF com sucesso.")
    void getEnderecoHospedeByCpf_Sucesso() throws Exception {
        Endereco endereco = new Endereco(1L, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);
        String cpf = "12345678901";

        Mockito.when(hospedeService.getEnderecoHospedeByCPF(cpf)).thenReturn(endereco);

        mockMvc.perform(get("/api/hospedes/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value("123"));
    }


    @Test
    @DisplayName("Teste para obter o endereço de um hóspede pelo CPF quando não encontrado.")
    void getEnderecoHospedeByCpf_NotFound() throws Exception {
        String cpf = "12345678901";

        Mockito.when(hospedeService.getEnderecoHospedeByCPF(cpf))
                .thenThrow(new ResourceNotFoundException("Hóspede sem endereço cadastrado"));

        mockMvc.perform(get("/api/hospedes/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}

package com.example.gerenciador.hotel.resources;


import com.example.gerenciador.hotel.entities.Endereco;
import com.example.gerenciador.hotel.entities.Hospede;

import com.example.gerenciador.hotel.repositories.EnderecoRepository;
import com.example.gerenciador.hotel.repositories.HospedeRepository;
import com.example.gerenciador.hotel.services.HospedeService;
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
@DisplayName("Testes de HospedeResource")
class HospedeResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private HospedeService hospedeService;

    @BeforeEach
    void setUp() {
        hospedeRepository.deleteAll();
        enderecoRepository.deleteAll();
    }


    @Test
    @DisplayName("Teste para criar um hóspede.")
    void criaHospede_Sucesso() throws Exception {
        Hospede hospede = new Hospede();
        hospede.setCpf("12345678901");
        hospede.setNome("João Silva");
        hospede.setDataNascimento(new Date());

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
        hospede.setDataNascimento(new Date());
        hospedeRepository.save(hospede);

        mockMvc.perform(get("/api/hospedes/12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João Silva"));
    }


    @Test
    @DisplayName("Teste para atualizar um hóspede.")
    void atualizaHospede_Sucesso() throws Exception {
        Hospede hospede = new Hospede();
        hospede.setCpf("12345678901");
        hospede.setNome("João Silva");
        hospede.setDataNascimento(new Date());
        hospedeRepository.save(hospede);

        Hospede hospedeAtualizado = new Hospede();
        hospedeAtualizado.setNome("Maria Silva");

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
        hospedeRepository.save(hospede);

        mockMvc.perform(post("/api/hospedes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hospede)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    @Test
    @DisplayName("Teste para criar endereço de um hóspede com sucesso.")
    void criaEnderecoHospede_Sucesso() throws Exception {
        String cpf = "12345678901";
        Endereco endereco = new Endereco(null, "Rua A", "123", "Cidade X", "Bairro Y", "Estado Z", null);

        Hospede hospede = new Hospede();
        hospede.setCpf(cpf);
        hospede.setNome("João Silva");
        hospede.setDataNascimento(new Date());

        hospedeRepository.save(hospede);


        mockMvc.perform(post("/api/hospedes/endereco/" + cpf)
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
    @DisplayName("Teste para obter o endereço de um hóspede pelo CPF quando não encontrado.")
    void getEnderecoHospedeByCpf_NotFound() throws Exception {
        String cpf = "12345678901";

        mockMvc.perform(get("/api/hospedes/endereco/" + cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}


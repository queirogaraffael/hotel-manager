package com.example.gerenciador.hotel.resources;

import com.example.gerenciador.hotel.domain.entities.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.entities.Funcionario;

import com.example.gerenciador.hotel.domain.repositories.ExtratoFuncionarioRepository;
import com.example.gerenciador.hotel.domain.repositories.FuncionarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExtratoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExtratoFuncionarioRepository extratoFuncionarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setUp() {
        extratoFuncionarioRepository.deleteAll();
        funcionarioRepository.deleteAll();
    }

    @Test
    void deveCriarExtratoFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("Funcionário Teste");
        funcionarioRepository.save(funcionario);

        ExtratoFuncionario extrato = new ExtratoFuncionario();
        extrato.setDataExtrato(new Date());
        extrato.setHorasTrabalhadas(40);
        extrato.setValorHora(15.0);
        extrato.setSalario(600.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/extratos/{cpf}", "12345678901")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(extrato)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.salario").value(600.0));
    }

    @Test
    void deveRetornarExtratoPorId() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("Funcionário Teste");
        funcionarioRepository.save(funcionario);

        ExtratoFuncionario extrato = new ExtratoFuncionario();
        extrato.setDataExtrato(new Date());
        extrato.setHorasTrabalhadas(40);
        extrato.setValorHora(15.0);
        extrato.setSalario(600.0);
        extratoFuncionarioRepository.save(extrato);

        Long extratoId = extrato.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/extratos/{idExtrato}", extratoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(extratoId));
    }

    @Test
    void deveBuscarExtratosPorCpfPaginados() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("Funcionário Teste");
        funcionarioRepository.save(funcionario);

        ExtratoFuncionario extrato1 = new ExtratoFuncionario();
        extrato1.setFuncionario(funcionario); // Associe o funcionário ao extrato
        extrato1.setDataExtrato(new Date());
        extrato1.setHorasTrabalhadas(40);
        extrato1.setValorHora(15.0);
        extrato1.setSalario(600.0);
        extratoFuncionarioRepository.save(extrato1);

        ExtratoFuncionario extrato2 = new ExtratoFuncionario();
        extrato2.setFuncionario(funcionario); // Associe o funcionário ao extrato
        extrato2.setDataExtrato(new Date());
        extrato2.setHorasTrabalhadas(45);
        extrato2.setValorHora(20.0);
        extrato2.setSalario(900.0);
        extratoFuncionarioRepository.save(extrato2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/extratos/cpf/{cpf}", "12345678901")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2));
    }


    @Test
    void deveAtualizarExtratoPorId() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("Funcionário Teste");
        funcionarioRepository.save(funcionario);

        ExtratoFuncionario extrato = new ExtratoFuncionario();
        extrato.setDataExtrato(new Date());
        extrato.setHorasTrabalhadas(40);
        extrato.setValorHora(15.0);
        extrato.setSalario(600.0);
        extratoFuncionarioRepository.save(extrato);

        Long extratoId = extrato.getId();

        ExtratoFuncionario extratoModificado = new ExtratoFuncionario();
        extratoModificado.setDataExtrato(new Date());
        extratoModificado.setHorasTrabalhadas(50);
        extratoModificado.setValorHora(20.0);
        extratoModificado.setSalario(1000.0);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/extratos/{id}", extratoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(extratoModificado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salario").value(1000.0));
    }

    @Test
    void deveDeletarExtratoPorId() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("Funcionário Teste");
        funcionarioRepository.save(funcionario);

        ExtratoFuncionario extrato = new ExtratoFuncionario();
        extrato.setDataExtrato(new Date());
        extrato.setHorasTrabalhadas(40);
        extrato.setValorHora(15.0);
        extrato.setSalario(600.0);
        extratoFuncionarioRepository.save(extrato);

        Long extratoId = extrato.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/extratos/{id}", extratoId))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/extratos/{idExtrato}", extratoId))
                .andExpect(status().isNotFound());
    }
}


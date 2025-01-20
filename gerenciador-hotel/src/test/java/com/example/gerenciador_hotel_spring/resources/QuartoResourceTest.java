package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.entities.Quarto;
import com.example.gerenciador_hotel_spring.enums.StatusQuarto;
import com.example.gerenciador_hotel_spring.enums.TipoQuarto;
import com.example.gerenciador_hotel_spring.repositories.QuartoRepository;
import com.example.gerenciador_hotel_spring.services.QuartoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes de QuartoResource")
class QuartoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuartoService quartoService;

    @Autowired
    private QuartoRepository quartoRepository;

    @BeforeEach
    public void setUp() {
        quartoRepository.deleteAll();
    }

    @Test
    @DisplayName("Teste para criar um novo quarto com sucesso.")
    void testCriarQuarto() throws Exception {
        Quarto quarto = Quarto.builder()
                .numero("101")
                .tipoQuarto(TipoQuarto.SOLTEIRO)
                .capacidade(2)
                .precoDiaria(100.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        mockMvc.perform(post("/api/quartos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quarto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numero").value("101"));
    }

    @Test
    @DisplayName("Teste para obter um quarto pelo número.")
    void testGetQuartoByNumero() throws Exception {
        Quarto quarto = Quarto.builder()
                .id(1L)
                .numero("101")
                .tipoQuarto(TipoQuarto.SOLTEIRO)
                .capacidade(2)
                .precoDiaria(100.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        quartoService.criarQuarto(quarto); // Criar quarto no banco de dados

        mockMvc.perform(get("/api/quartos/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("101"));
    }

    @Test
    @DisplayName("Teste para editar um quarto existente pelo número.")
    void testEditQuartoByNumero() throws Exception {
        Quarto quarto = Quarto.builder()
                .numero("101")
                .tipoQuarto(TipoQuarto.SOLTEIRO)
                .capacidade(2)
                .precoDiaria(100.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        quartoService.criarQuarto(quarto);

        Quarto quartoModificado = Quarto.builder()
                .numero("102")
                .tipoQuarto(TipoQuarto.CASAL)
                .capacidade(4)
                .precoDiaria(200.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        mockMvc.perform(put("/api/quartos/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quartoModificado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("102"));
    }

    @Test
    @DisplayName("Teste para buscar quartos por tipo.")
    void testBuscarQuartosPorTipo() throws Exception {

        Quarto quarto = Quarto.builder()
                .numero("101")
                .tipoQuarto(TipoQuarto.SOLTEIRO)
                .capacidade(2)
                .precoDiaria(100.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        quartoService.criarQuarto(quarto);

        mockMvc.perform(get("/api/quartos/por-tipo/SOLTEIRO?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numero").value("101"));
    }

    @Test
    @DisplayName("Teste para buscar quartos por tipo e status.")
    void testBuscarQuartosPorTipoEStatus() throws Exception {
        Quarto quarto = Quarto.builder()
                .numero("101")
                .tipoQuarto(TipoQuarto.SOLTEIRO)
                .capacidade(2)
                .precoDiaria(100.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        quartoService.criarQuarto(quarto);

        mockMvc.perform(get("/api/quartos/por-tipo/SOLTEIRO/status/DISPONIVEL?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numero").value("101"));
    }

    @Test
    @DisplayName("Teste para buscar quartos por status.")
    void testBuscarQuartosPorStatus() throws Exception {
        Quarto quarto = Quarto.builder()
                .numero("101")
                .tipoQuarto(TipoQuarto.SOLTEIRO)
                .capacidade(2)
                .precoDiaria(100.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        quartoService.criarQuarto(quarto);

        mockMvc.perform(get("/api/quartos/por-status/DISPONIVEL?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numero").value("101"));
    }

    @Test
    @DisplayName("Teste para buscar quartos disponíveis por tipo e datas.")
    void testBuscarQuartosDisponiveis() throws Exception {
        Quarto quarto = Quarto.builder()
                .numero("101")
                .tipoQuarto(TipoQuarto.SOLTEIRO)
                .capacidade(2)
                .precoDiaria(100.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        quartoService.criarQuarto(quarto); // Criar quarto no banco de dados

        mockMvc.perform(get("/api/quartos/quartos/disponiveis")
                        .param("tipoQuarto", "SOLTEIRO")
                        .param("dataEntrada", "2023-09-01")
                        .param("dataSaida", "2023-09-05")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numero").value("101"));
    }
}



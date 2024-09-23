package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO;
import com.example.gerenciador_hotel_spring.entities.Quarto;
import com.example.gerenciador_hotel_spring.enums.StatusQuarto;
import com.example.gerenciador_hotel_spring.enums.TipoQuarto;
import com.example.gerenciador_hotel_spring.services.QuartoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
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

    @Mock
    private QuartoService quartoService;

    @InjectMocks
    private QuartoResource quartoResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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

        when(quartoService.criarQuarto(any(Quarto.class))).thenReturn(quarto);

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

        when(quartoService.getQuartoByNumero("101")).thenReturn(quarto);

        mockMvc.perform(get("/api/quartos/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("101"));
    }

    @Test
    @DisplayName("Teste para editar um quarto existente pelo número.")
    void testEditeQuartoByNumero() throws Exception {
        Quarto quartoModificado = Quarto.builder()
                .numero("102")
                .tipoQuarto(TipoQuarto.CASAL)
                .capacidade(4)
                .precoDiaria(200.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        Quarto quartoAtualizado = Quarto.builder()
                .id(1L)
                .numero("102")
                .tipoQuarto(TipoQuarto.CASAL)
                .capacidade(4)
                .precoDiaria(200.0)
                .statusQuarto(StatusQuarto.DISPONIVEL)
                .build();

        when(quartoService.editaQuartoByNumero(eq("101"), any(Quarto.class))).thenReturn(quartoAtualizado);

        mockMvc.perform(put("/api/quartos/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quartoModificado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("102"));
    }

    @Test
    @DisplayName("Teste para buscar quartos por tipo.")
    void testBuscarQuartosPorTipo() throws Exception {
        Page<QuartoResponseDTO> page = new PageImpl<>(List.of(
                new QuartoResponseDTO(1L, "101", TipoQuarto.SOLTEIRO)),
                PageRequest.of(0, 10), 1);

        when(quartoService.buscarQuartosDTOPorTipoPaginadas(TipoQuarto.SOLTEIRO, PageRequest.of(0, 10))).thenReturn(page);

        mockMvc.perform(get("/api/quartos/por-tipo/SOLTEIRO?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numero").value("101"));
    }

    @Test
    @DisplayName("Teste para buscar quartos por tipo e status.")
    void testBuscarQuartosPorTipoEStatus() throws Exception {
        Page<QuartoResponseDTO> page = new PageImpl<>(List.of(
                new QuartoResponseDTO(1L, "101", TipoQuarto.SOLTEIRO)),
                PageRequest.of(0, 10), 1);

        when(quartoService.buscarQuartosDTOPorTipoEPorStatusPaginadas(TipoQuarto.SOLTEIRO, StatusQuarto.DISPONIVEL, PageRequest.of(0, 10))).thenReturn(page);

        mockMvc.perform(get("/api/quartos/por-tipo/SOLTEIRO/status/DISPONIVEL?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numero").value("101"));
    }

    @Test
    @DisplayName("Teste para buscar quartos por status.")
    void testBuscarQuartosPorStatus() throws Exception {
        Page<QuartoResponseDTO> page = new PageImpl<>(List.of(
                new QuartoResponseDTO(1L, "101", TipoQuarto.SOLTEIRO)),
                PageRequest.of(0, 10), 1);

        when(quartoService.buscarQuartosDTOPorStatusPaginadas(StatusQuarto.DISPONIVEL, PageRequest.of(0, 10))).thenReturn(page);

        mockMvc.perform(get("/api/quartos/por-status/DISPONIVEL?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numero").value("101"));
    }

    @Test
    @DisplayName("Teste para buscar quartos disponíveis por tipo e datas.")
    void testBuscarQuartosDisponiveis() throws Exception {
        Page<QuartoResponseDTO> page = new PageImpl<>(List.of(
                new QuartoResponseDTO(1L, "101", TipoQuarto.SOLTEIRO)),
                PageRequest.of(0, 10), 1);

        when(quartoService.buscaQuartoPorTipoDisponiveisPorData(any(TipoQuarto.class), any(Date.class), any(Date.class), any(PageRequest.class))).thenReturn(page);

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


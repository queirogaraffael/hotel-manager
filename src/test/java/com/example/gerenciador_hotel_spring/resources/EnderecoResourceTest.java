package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.EnderecoRepository;
import com.example.gerenciador_hotel_spring.services.EnderecoService;
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

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Endereço")
class EnderecoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnderecoService enderecoService;

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Test
    @DisplayName("Teste para atualizar endereço com sucesso.")
    void testModificaEnderecoById_Success() throws Exception {
        Long id = 1L;

        Endereco enderecoExistente = new Endereco(id, "Rua Antiga", "123", "Cidade Antiga", "Bairro Antigo", "Estado Antigo", null);
        Endereco enderecoAtualizado = new Endereco(null, "Rua Nova", "456", "Cidade Nova", "Bairro Novo", "Estado Novo", null);

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(enderecoExistente));

        when(enderecoService.modificaEnderecoById(Mockito.eq(id), Mockito.any(Endereco.class)))
                .thenReturn(enderecoAtualizado);

        mockMvc.perform(put("/enderecos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rua").value("Rua Nova"))
                .andExpect(jsonPath("$.numero").value("456"))
                .andExpect(jsonPath("$.cidade").value("Cidade Nova"))
                .andExpect(jsonPath("$.bairro").value("Bairro Novo"))
                .andExpect(jsonPath("$.estado").value("Estado Novo"));
    }




    @Test
    @DisplayName("Teste para atualizar endereço não encontrado.")
    void testModificaEnderecoById_NotFound() throws Exception {
        Long id = 999L;
        Endereco enderecoAtualizado = new Endereco(null, "Rua Nova", "456", "Cidade Nova", "Bairro Novo", "Estado Novo", null);

        when(enderecoService.modificaEnderecoById(id, enderecoAtualizado))
                .thenThrow(new ResourceNotFoundException("Endereco com este id " + id + " não encontrado para modificação."));

        mockMvc.perform(put("/enderecos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoAtualizado)))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Teste para deletar endereço com sucesso.")
    void testDeletaEnderecoPorId_Success() throws Exception {
        Long id = 1L;

        doNothing().when(enderecoService).deletaEnderecoById(id);

        mockMvc.perform(delete("/enderecos/{id}", id))
                .andExpect(status().isNoContent());
    }
}


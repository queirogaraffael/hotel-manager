package com.example.gerenciador.hotel.resources;

import com.example.gerenciador.hotel.entities.Hospede;
import com.example.gerenciador.hotel.entities.Quarto;
import com.example.gerenciador.hotel.entities.Reserva;

import com.example.gerenciador.hotel.enums.StatusQuarto;
import com.example.gerenciador.hotel.enums.StatusReserva;
import com.example.gerenciador.hotel.enums.TipoQuarto;
import com.example.gerenciador.hotel.repositories.HospedeRepository;
import com.example.gerenciador.hotel.repositories.QuartoRepository;
import com.example.gerenciador.hotel.repositories.ReservaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
@DisplayName("Testes de ReservaResource")
class ReservaResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private HospedeRepository hospedeRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        quartoRepository.deleteAll();
        hospedeRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar reserva com sucesso")
    void deveCriarReserva() throws Exception {
        Quarto quarto = new Quarto();
        quarto.setNumero("101");
        quarto.setCapacidade(2);
        quarto.setPrecoDiaria(150.0);
        quarto.setStatusQuarto(StatusQuarto.DISPONIVEL);
        quarto.setTipoQuarto(TipoQuarto.SOLTEIRO);
        quartoRepository.save(quarto);

        Hospede hospede = new Hospede();
        hospede.setCpf("12345678901");
        hospede.setNome("Hóspede Teste");
        hospedeRepository.save(hospede);

        Reserva reserva = new Reserva();
        reserva.setDataEntrada(new Date());
        reserva.setDataSaida(new Date());
        reserva.setNumeroHospedes(2);
        reserva.setStatusReserva(StatusReserva.AGENDADO);
        reserva.setValorTotal(300.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservas/{cpf}/{numeroQuarto}", "12345678901", "101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.statusReserva").value("AGENDADO"));
    }

    @Test
    @DisplayName("Deve retornar reserva por ID com sucesso")
    void deveRetornarReservaPorId() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(new Date());
        reserva.setDataSaida(new Date());
        reserva.setNumeroHospedes(2);
        reserva.setStatusReserva(StatusReserva.AGENDADO);
        reserva.setValorTotal(300.0);
        reservaRepository.save(reserva);

        Long reservaId = reserva.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas/{id}", reservaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reservaId));
    }

    @Test
    @DisplayName("Deve atualizar status da reserva com sucesso")
    void deveAtualizarStatusReserva() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(new Date());
        reserva.setDataSaida(new Date());
        reserva.setNumeroHospedes(2);
        reserva.setStatusReserva(StatusReserva.AGENDADO);
        reserva.setValorTotal(300.0);
        reservaRepository.save(reserva);

        Long reservaId = reserva.getId();
        StatusReserva status = StatusReserva.FINALIZADO;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/reservas/{id}/status", reservaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(status)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusReserva").value("FINALIZADO"));
    }

    @Test
    @DisplayName("Deve buscar reservas agendadas e em uso por CPF")
    void deveBuscarReservasAgendadasEEmUsoPorCpf() throws Exception {
        String cpf = "12345678901";

        Reserva reserva = new Reserva();
        reserva.setDataEntrada(new Date());
        reserva.setDataSaida(new Date());
        reserva.setNumeroHospedes(2);
        reserva.setStatusReserva(StatusReserva.AGENDADO);
        reserva.setValorTotal(300.0);
        reservaRepository.save(reserva);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas/reservas/agendadas-em-uso/por-cpf/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    @DisplayName("Deve buscar reservas por hóspede e status")
    void deveBuscarReservasPorHospedeEStatus() throws Exception {
        String cpfHospede = "12345678901";
        String status = "FINALIZADO";

        Reserva reserva = new Reserva();
        reserva.setDataEntrada(new Date());
        reserva.setDataSaida(new Date());
        reserva.setNumeroHospedes(2);
        reserva.setStatusReserva(StatusReserva.FINALIZADO);
        reserva.setValorTotal(300.0);
        reservaRepository.save(reserva);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas/por-hospede/{cpfHospede}/status/{status}", cpfHospede, status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    @DisplayName("Deve buscar reservas finalizadas e canceladas por CPF")
    void deveBuscarReservasFinalizadasECanceladasPorCpf() throws Exception {
        String cpf = "12345678901";

        Reserva reserva = new Reserva();
        reserva.setDataEntrada(new Date());
        reserva.setDataSaida(new Date());
        reserva.setNumeroHospedes(2);
        reserva.setStatusReserva(StatusReserva.CANCELADO);
        reserva.setValorTotal(300.0);
        reservaRepository.save(reserva);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas/reservas/finalizadas-canceladas/por-cpf/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}


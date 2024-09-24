package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.dtos.ReservaResponseDTO;
import com.example.gerenciador_hotel_spring.entities.Reserva;
import com.example.gerenciador_hotel_spring.enums.StatusReserva;
import com.example.gerenciador_hotel_spring.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaResource {

    @Autowired
    private ReservaService reservaService;

    @Operation(summary = "Criar nova reserva", description = "Cria uma nova reserva. Quarto tem que estar disponivel para data e hóspede precisa já estar cadastrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("{cpf}/{numeroQuarto}")
    public ResponseEntity<Reserva> criarReserva(@PathVariable String cpf, @PathVariable String numeroQuarto, @RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.criarReserva(reserva, cpf, numeroQuarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
    }


    @Operation(summary = "Obter reserva por ID", description = "Recupera uma reserva com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Reserva reserva = reservaService.getReservaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reserva);
    }


    @Operation(summary = "Modificar status da reserva", description = "Atualiza o status de uma reserva com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status da reserva atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<Reserva> modificaStatusReservaById(@PathVariable Long id, @RequestBody StatusReserva statusReserva) {
        Reserva reservaAtualizada = reservaService.modificaStatusReservaById(id, statusReserva);
        return ResponseEntity.status(HttpStatus.OK).body(reservaAtualizada);
    }


    @Operation(summary = "Busca paginada de reservas AGENDADAS e EM USO por CPF", description = "Recupera reservas AGENDADAS e EM USO paginadas de um hóspede com base no CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma reserva encontrada para o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/reservas/agendadas-em-uso/por-cpf/{cpf}")
    public ResponseEntity<Page<ReservaResponseDTO>> buscarReservasDTOAgendadasEmUsoPorCPF(
            @PathVariable String cpf,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<ReservaResponseDTO> reservasDTO = reservaService.buscarReservasDTOAgendadasEmUsoPorCPF(cpf,pageable);
        return ResponseEntity.ok(reservasDTO);
    }


    @Operation(summary = "Busca paginada de reservas DTO por hóspede e status", description = "Recupera reservas de um hóspede com base no CPF e status, paginadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma reserva encontrada para o hóspede ou status fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/por-hospede/{cpfHospede}/status/{status}")
    public ResponseEntity<Page<ReservaResponseDTO>> buscarReservasPorHospedeEStatus(
            @PathVariable String cpfHospede,
            @PathVariable StatusReserva status,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<ReservaResponseDTO> reservasDTO = reservaService.buscarReservasDTOPorHospedeEStatusPaginadas(cpfHospede, status, pageable);

        return ResponseEntity.ok(reservasDTO);
    }


    @Operation(summary = "Busca paginada de reservas FINALIZADAS e CANCELADAS (Histórico) por CPF.", description = "Recupera histórico (reservas finalizadas e canceladas) paginado de um hóspede com base no CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma reserva encontrada para o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/reservas/finalizadas-canceladas/por-cpf/{cpf}")
    public ResponseEntity<Page<ReservaResponseDTO>> buscarReservasFinalizadasCanceladasPorCPF(
            @PathVariable String cpf,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<ReservaResponseDTO> reservasDTO = reservaService.buscaReservasDTOHospedeFinalizadasCanceladasByCPF(cpf, pageable);
        return ResponseEntity.ok(reservasDTO);
    }


}

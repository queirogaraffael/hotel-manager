package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.port.in.ReservaUseCase;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.reserva.ReservaRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.reserva.ReservaResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.ReservaWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaUseCase reservaUseCase;
    private final ReservaWebMapper mapper;

    @Operation(summary = "Criar reserva para hóspede")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede ou quarto não encontrado"),
            @ApiResponse(responseCode = "409", description = "Quarto não está disponível para a data")
    })
    @PostMapping("/{cpfHospede}")
    public ResponseEntity<ReservaResponseDTO> criarReserva(
            @PathVariable String cpfHospede,
            @Valid @RequestBody ReservaRequestDTO dto) {
        Reserva reserva = reservaUseCase.criarReserva(
                cpfHospede, dto.getNumeroQuarto(),
                dto.getDataEntrada(), dto.getDataSaida(),
                dto.getNumeroHospedes()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(reserva));
    }

    @Operation(summary = "Buscar reserva por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(reservaUseCase.buscarReservaPorId(id)));
    }

    @Operation(summary = "Modificar status da reserva")
    @PutMapping("/{id}/status")
    public ResponseEntity<ReservaResponseDTO> modificarStatus(
            @PathVariable Long id,
            @RequestParam StatusReserva statusReserva) {
        Reserva reserva = reservaUseCase.modificarStatusReserva(id, statusReserva);
        return ResponseEntity.ok(mapper.toResponse(reserva));
    }

    @Operation(summary = "Buscar reservas agendadas/em uso por CPF do hóspede")
    @GetMapping("/agendadas-em-uso/por-cpf/{cpf}")
    public ResponseEntity<Page<ReservaResponseDTO>> buscarAgendadasEmUso(
            @PathVariable String cpf,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(reservaUseCase.buscarReservasAgendadasEmUsoPorCpf(cpf, pageable).map(mapper::toResponse));
    }

    @Operation(summary = "Buscar reservas finalizadas/canceladas por CPF do hóspede")
    @GetMapping("/historico/por-cpf/{cpf}")
    public ResponseEntity<Page<ReservaResponseDTO>> buscarHistorico(
            @PathVariable String cpf,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(reservaUseCase.buscarReservasFinalizadasCanceladasPorCpf(cpf, pageable).map(mapper::toResponse));
    }

    @Operation(summary = "Buscar reservas por hóspede e status")
    @GetMapping("/por-hospede/{cpf}/status/{status}")
    public ResponseEntity<Page<ReservaResponseDTO>> buscarPorHospedeEStatus(
            @PathVariable String cpf,
            @PathVariable StatusReserva status,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(reservaUseCase.buscarReservasPorHospedeEStatus(cpf, status, pageable).map(mapper::toResponse));
    }
}

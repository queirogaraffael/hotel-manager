package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.QuartoUseCase;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.quarto.QuartoRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.quarto.QuartoResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.quarto.QuartoUpdateDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.QuartoWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quartos")
@RequiredArgsConstructor
public class QuartoController {

    private final QuartoUseCase quartoUseCase;
    private final QuartoWebMapper mapper;

    @Operation(summary = "Criar novo quarto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quarto criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Quarto já existe")
    })
    @PostMapping
    public ResponseEntity<QuartoResponseDTO> criarQuarto(@Valid @RequestBody QuartoRequestDTO dto) {
        Quarto quarto = quartoUseCase.criarQuarto(
                dto.getNumero(), dto.getTipoQuarto(), dto.getCapacidade(),
                dto.getPrecoDiaria(), dto.getStatusQuarto()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(quarto));
    }

    @Operation(summary = "Buscar quarto por número")
    @GetMapping("/{numero}")
    public ResponseEntity<QuartoResponseDTO> getQuartoByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(mapper.toResponse(quartoUseCase.buscarQuartoPorNumero(numero)));
    }

    @Operation(summary = "Editar quarto por número")
    @PutMapping("/{numero}")
    public ResponseEntity<QuartoResponseDTO> editarQuarto(
            @PathVariable String numero,
            @RequestBody QuartoUpdateDTO dto) {
        Quarto atualizado = quartoUseCase.editarQuarto(
                numero, dto.getTipoQuarto(), dto.getCapacidade(), dto.getPrecoDiaria(), dto.getStatusQuarto()
        );
        return ResponseEntity.ok(mapper.toResponse(atualizado));
    }

    @Operation(summary = "Modificar status do quarto")
    @PatchMapping("/{numero}/status")
    public ResponseEntity<QuartoResponseDTO> modificarStatus(
            @PathVariable String numero,
            @RequestParam StatusQuarto statusQuarto) {
        Quarto quarto = quartoUseCase.modificarStatusQuarto(numero, statusQuarto);
        return ResponseEntity.ok(mapper.toResponse(quarto));
    }

    @Operation(summary = "Buscar quartos por tipo (paginado)")
    @GetMapping("/por-tipo/{tipoQuarto}")
    public ResponseEntity<Page<QuartoResponseDTO>> buscarPorTipo(
            @PathVariable TipoQuarto tipoQuarto,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(quartoUseCase.buscarQuartosPorTipo(tipoQuarto, pageable).map(mapper::toResponse));
    }

    @Operation(summary = "Buscar quartos por tipo e status (paginado)")
    @GetMapping("/por-tipo/{tipoQuarto}/status/{statusQuarto}")
    public ResponseEntity<Page<QuartoResponseDTO>> buscarPorTipoEStatus(
            @PathVariable TipoQuarto tipoQuarto,
            @PathVariable StatusQuarto statusQuarto,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(quartoUseCase.buscarQuartosPorTipoEStatus(tipoQuarto, statusQuarto, pageable).map(mapper::toResponse));
    }

    @Operation(summary = "Buscar quartos por status (paginado)")
    @GetMapping("/por-status/{statusQuarto}")
    public ResponseEntity<Page<QuartoResponseDTO>> buscarPorStatus(
            @PathVariable StatusQuarto statusQuarto,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(quartoUseCase.buscarQuartosPorStatus(statusQuarto, pageable).map(mapper::toResponse));
    }

    @Operation(summary = "Buscar quartos disponíveis por tipo e data")
    @GetMapping("/disponiveis")
    public ResponseEntity<List<QuartoResponseDTO>> buscarDisponiveis(
            @RequestParam TipoQuarto tipoQuarto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataEntrada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataSaida) {
        List<QuartoResponseDTO> quartos = quartoUseCase
                .buscarQuartosDisponiveisPorTipoEData(tipoQuarto, dataEntrada, dataSaida)
                .stream().map(mapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(quartos);
    }
}

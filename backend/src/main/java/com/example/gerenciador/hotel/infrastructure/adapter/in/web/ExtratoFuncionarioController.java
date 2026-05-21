package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.port.in.extratofuncionario.*;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.extratofuncionario.ExtratoFuncionarioRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.extratofuncionario.ExtratoFuncionarioResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.ExtratoFuncionarioWebMapper;
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
@RequestMapping("/api/extratos")
@RequiredArgsConstructor
public class ExtratoFuncionarioController {

    private final CriarExtratoUseCase criarExtratoUseCase;
    private final BuscarExtratoPorIdUseCase buscarExtratoPorIdUseCase;
    private final BuscarExtratosPorCpfUseCase buscarExtratosPorCpfUseCase;
    private final EditarExtratoUseCase editarExtratoUseCase;
    private final DeletarExtratoUseCase deletarExtratoUseCase;

    private final ExtratoFuncionarioWebMapper mapper;

    @Operation(summary = "Criar extrato para funcionário pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Extrato criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "409", description = "Extrato já existe para o mês referente")
    })
    @PostMapping("/{cpf}")
    public ResponseEntity<ExtratoFuncionarioResponseDTO> criarExtrato(
            @PathVariable String cpf,
            @Valid @RequestBody ExtratoFuncionarioRequestDTO dto) {
        ExtratoFuncionario extrato = criarExtratoUseCase.criarExtrato(
                cpf, dto.getDataExtrato(), dto.getHorasTrabalhadas(), dto.getValorHora(), dto.getSalario()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(extrato));
    }

    @Operation(summary = "Buscar extrato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExtratoFuncionarioResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(buscarExtratoPorIdUseCase.buscarExtratoPorId(id)));
    }

    @Operation(summary = "Buscar extratos por CPF do funcionário (paginado)")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Page<ExtratoFuncionarioResponseDTO>> getByCpf(
            @PathVariable String cpf,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(buscarExtratosPorCpfUseCase.buscarExtratosPorCpf(cpf, pageable).map(mapper::toResponse));
    }

    @Operation(summary = "Editar extrato por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ExtratoFuncionarioResponseDTO> editar(
            @PathVariable Long id,
            @RequestBody ExtratoFuncionarioRequestDTO dto) {
        ExtratoFuncionario atualizado = editarExtratoUseCase.editarExtrato(
                id, dto.getDataExtrato(), dto.getHorasTrabalhadas(), dto.getValorHora(), dto.getSalario()
        );
        return ResponseEntity.ok(mapper.toResponse(atualizado));
    }

    @Operation(summary = "Deletar extrato por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarExtratoUseCase.deletarExtrato(id);
        return ResponseEntity.noContent().build();
    }
}

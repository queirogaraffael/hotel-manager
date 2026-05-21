package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.port.in.hospede.*;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.hospede.HospedeResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.EnderecoWebMapper;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.HospedeWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospedes")
@RequiredArgsConstructor
public class HospedeController {

    private final BuscarHospedePorCpfInputPort buscarHospedePorCpfUseCase;
    private final CriarEnderecoParaHospedeInputPort criarEnderecoParaHospedeUseCase;
    private final BuscarEnderecoHospedePorCpfInputPort buscarEnderecoHospedePorCpfUseCase;

    private final HospedeWebMapper hospedeMapper;
    private final EnderecoWebMapper enderecoMapper;

    @Operation(summary = "Buscar hóspede por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hóspede encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede não encontrado")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<HospedeResponseDTO> getHospedeByCpf(@PathVariable String cpf) {
        Hospede hospede = buscarHospedePorCpfUseCase.buscarHospedePorCpf(cpf);
        return ResponseEntity.ok(hospedeMapper.toResponse(hospede));
    }

    @Operation(summary = "Criar endereço para hóspede", description = "Se o hóspede já tiver endereço, o novo substituirá o anterior.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede não encontrado")
    })
    @PostMapping("/endereco/{cpf}")
    public ResponseEntity<EnderecoResponseDTO> criaEnderecoHospede(
            @PathVariable String cpf,
            @RequestBody EnderecoRequestDTO dto) {
        Endereco enderecoSalvo = criarEnderecoParaHospedeUseCase.criarEnderecoParaHospede(cpf, enderecoMapper.toDomain(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoMapper.toResponse(enderecoSalvo));
    }

    @Operation(summary = "Buscar endereço de hóspede pelo CPF")
    @GetMapping("/endereco/{cpf}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoHospedeByCpf(@PathVariable String cpf) {
        Endereco endereco = buscarEnderecoHospedePorCpfUseCase.buscarEnderecoHospedePorCpf(cpf);
        return ResponseEntity.ok(enderecoMapper.toResponse(endereco));
    }
}

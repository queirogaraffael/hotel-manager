package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.FuncionarioUseCase;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.funcionario.FuncionarioResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.funcionario.FuncionarioUpdateDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.EnderecoWebMapper;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.FuncionarioWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioUseCase funcionarioUseCase;
    private final FuncionarioWebMapper funcionarioMapper;
    private final EnderecoWebMapper enderecoMapper;

    @Operation(summary = "Buscar funcionário por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionarioByCpf(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioUseCase.buscarFuncionarioPorCpf(cpf);
        return ResponseEntity.ok(funcionarioMapper.toResponse(funcionario));
    }

    @Operation(summary = "Buscar funcionários por nome (paginado)")
    @GetMapping("/nome")
    public ResponseEntity<Page<FuncionarioResponseDTO>> buscarPorNome(
            @RequestParam String nome,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<FuncionarioResponseDTO> resultado = funcionarioUseCase
                .buscarFuncionariosPorNome(nome, pageable)
                .map(funcionarioMapper::toResponse);
        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Atualizar cargo e turno do funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(
            @PathVariable String cpf,
            @RequestBody FuncionarioUpdateDTO dto) {
        Funcionario atualizado = funcionarioUseCase.editarFuncionario(cpf, null, dto.getCargo(), dto.getTurno());
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioMapper.toResponse(atualizado));
    }

    @Operation(summary = "Criar endereço para funcionário", description = "Se já tiver endereço, o novo substituirá o anterior.")
    @PostMapping("/endereco/{cpf}")
    public ResponseEntity<EnderecoResponseDTO> criaEnderecoFuncionario(
            @PathVariable String cpf,
            @RequestBody EnderecoRequestDTO dto) {
        Endereco enderecoSalvo = funcionarioUseCase.criarEnderecoParaFuncionario(cpf, enderecoMapper.toDomain(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoMapper.toResponse(enderecoSalvo));
    }

    @Operation(summary = "Buscar endereço de funcionário por CPF")
    @GetMapping("/endereco/{cpf}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoFuncionarioByCpf(@PathVariable String cpf) {
        Endereco endereco = funcionarioUseCase.buscarEnderecoFuncionarioPorCpf(cpf);
        return ResponseEntity.ok(enderecoMapper.toResponse(endereco));
    }
}

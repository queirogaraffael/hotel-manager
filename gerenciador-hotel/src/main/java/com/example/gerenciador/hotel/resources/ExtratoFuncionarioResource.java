package com.example.gerenciador.hotel.resources;

import com.example.gerenciador.hotel.shared.dtos.extratoFuncionario.ExtratoFuncionarioDTO;
import com.example.gerenciador.hotel.shared.dtos.extratoFuncionario.ExtratoFuncionarioResponseDTO;
import com.example.gerenciador.hotel.domain.entities.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.services.ExtratoFuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/extratos")
public class ExtratoFuncionarioResource {

    @Autowired
    private ExtratoFuncionarioService extratoFuncionarioService;

    @PostMapping("/{cpf}")
    @Operation(summary = "Cria um extrato para o funcionário", description = "Cria um novo extrato para o funcionário com o CPF fornecido. Se existir extrato para o mes/ano referente, o programa lançará uma exceção.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Extrato criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<ExtratoFuncionarioDTO> criaExtratoFuncionario(
            @PathVariable String cpf,
            @RequestBody ExtratoFuncionarioDTO extratoFuncionarioDTO) {

        ExtratoFuncionarioDTO extrato = extratoFuncionarioService.criaExtratoFuncionario(cpf, extratoFuncionarioDTO);
        return ResponseEntity.status(201).body(extrato);
    }


    @GetMapping("/{idExtrato}")
    @Operation(summary = "Obtém um extrato único pelo ID", description = "Retorna o extrato correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extrato encontrado"),
            @ApiResponse(responseCode = "404", description = "Extrato não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<ExtratoFuncionario> getExtratoFuncionarioUnicoById(
            @PathVariable Long idExtrato) {
        ExtratoFuncionario extrato = extratoFuncionarioService.getExtratoFuncionarioUnicoById(idExtrato);
        return ResponseEntity.ok(extrato);
    }


    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Obtém extratos DTO paginados pelo CPF", description = "Retorna uma lista paginada de extratos para o funcionário com o CPF fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de extratos encontrada"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Page<ExtratoFuncionarioResponseDTO>> getExtratosFuncionarioDTOPorCPFPaginados(
            @PathVariable String cpf,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<ExtratoFuncionarioResponseDTO> extratos = extratoFuncionarioService.getExtratosFuncionarioDTOPorCPFPaginados(cpf, pageable);
        return ResponseEntity.ok(extratos);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Edita um extrato existente pelo ID", description = "Atualiza os dados do extrato correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extrato atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Extrato não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<ExtratoFuncionarioDTO> editaExtratoFuncionarioById(
            @PathVariable Long id,
            @RequestBody ExtratoFuncionarioDTO extratoFuncionarioModificado) {
        ExtratoFuncionarioDTO extratoAtualizado = extratoFuncionarioService.editaExtratoFuncionarioById(id, extratoFuncionarioModificado);
        return ResponseEntity.ok(extratoAtualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um extrato pelo ID", description = "Remove o extrato correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Extrato deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Extrato não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deletaExtratoFuncionarioById(
            @PathVariable Long id) {
        extratoFuncionarioService.deletaExtratoFuncionarioById(id);
        return ResponseEntity.noContent().build();
    }
}

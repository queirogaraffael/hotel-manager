package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.dtos.ExtratoFuncionarioDTO;
import com.example.gerenciador_hotel_spring.entities.ExtratoFuncionario;
import com.example.gerenciador_hotel_spring.services.ExtratoFuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/extratos")
public class ExtratoFuncionarioResource {

    @Autowired
    private ExtratoFuncionarioService extratoFuncionarioService;

    @PostMapping("/{cpf}")
    @Operation(summary = "Cria um extrato para o funcionário", description = "Cria um novo extrato para o funcionário com o CPF fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Extrato criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<ExtratoFuncionario> criaExtratoFuncionario(
            @PathVariable String cpf,
            @RequestBody ExtratoFuncionario extrato) {
        ExtratoFuncionario novoExtrato = extratoFuncionarioService.criaExtratoFuncionario(cpf, extrato);
        return ResponseEntity.status(201).body(novoExtrato);
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
    @Operation(summary = "Obtém extratos paginados pelo CPF", description = "Retorna uma lista paginada de extratos para o funcionário com o CPF fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de extratos encontrada"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Page<ExtratoFuncionarioDTO>> getExtratosFuncionarioDTOPorCPFPaginados(
            @PathVariable String cpf,
            @RequestParam int page,
            @RequestParam int size) {
        Page<ExtratoFuncionarioDTO> extratos = extratoFuncionarioService.getExtratosFuncionarioDTOPorCPFPaginados(cpf, page, size);
        return ResponseEntity.ok(extratos);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Edita um extrato existente pelo ID", description = "Atualiza os dados do extrato correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extrato atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Extrato não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<ExtratoFuncionario> editaExtratoFuncionarioById(
            @PathVariable Long id,
            @RequestBody ExtratoFuncionario extratoFuncionarioModificado) {
        ExtratoFuncionario extratoAtualizado = extratoFuncionarioService.editaExtratoFuncionarioById(id, extratoFuncionarioModificado);
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

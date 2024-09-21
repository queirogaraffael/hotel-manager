package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.entities.Quarto;
import com.example.gerenciador_hotel_spring.services.QuartoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quartos")
public class QuartoResource {

    @Autowired
    QuartoService quartoService;

    @Operation(summary = "Cria novo quarto", description = "Cria um quarto com um número único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quarto criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito: Quarto já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<Quarto> criarQuarto(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.criarQuarto(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoQuarto);
    }


    @Operation(summary = "Obter quarto por número", description = "Recupera um quarto com base no número fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{numero}")
    public ResponseEntity<Quarto> getQuartoByNumero(@PathVariable String numero) {
        Quarto quarto = quartoService.getQuartoByNumero(numero);
        return ResponseEntity.status(HttpStatus.OK).body(quarto);
    }


    @Operation(summary = "Editar quarto por número", description = "Atualiza as informações de um quarto com base no número fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito: Quarto com o mesmo número já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{numero}")
    public ResponseEntity<Quarto> editaQuartoByNumero(@PathVariable String numero, @RequestBody Quarto quartoModificado) {
        Quarto quartoAtualizado = quartoService.editaQuartoByNumero(numero, quartoModificado);
        return ResponseEntity.ok(quartoAtualizado);
    }
}

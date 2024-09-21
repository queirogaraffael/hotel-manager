package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereços", description = "Operações em comum de Endereço para todas as entidades relacionadas a Endereço.")
public class EnderecoResource {

    @Autowired
    EnderecoService enderecoService;

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados do endereco.", description = "Atualiza todos os dados do endereço.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Endereco> modificaEnderecoById(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        Endereco endereco = enderecoService.modificaEnderecoById(id, enderecoAtualizado);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }


    @Operation(summary = "Deleta endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaEnderecoPorId(@PathVariable Long id){
        enderecoService.deletaEnderecoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

package com.example.gerenciador.hotel.infrastructure.adapter.in.web;


import com.example.gerenciador.hotel.domain.usecase.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/enderecos")
@Tag(name = "Endereços", description = "Operações comuns de endereço para todas as entidades que se relacionam com ele.")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    /*
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


     */
}

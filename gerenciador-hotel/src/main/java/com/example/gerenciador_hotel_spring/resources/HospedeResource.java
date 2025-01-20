package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.dtos.hospede.HospedeCreateDTO;
import com.example.gerenciador_hotel_spring.dtos.hospede.HospedeUpdateDTO;
import com.example.gerenciador_hotel_spring.entities.Endereco;
import com.example.gerenciador_hotel_spring.entities.Hospede;
import com.example.gerenciador_hotel_spring.services.HospedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeResource {

    @Autowired
    private HospedeService hospedeService;

    @Operation(summary = "Cria novo hóspede", description = "Não adiciona com endereço ou extratos. O CPF precisa ter exatamente 11 caracteres.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hóspede criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Conflito: Hóspede já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<HospedeCreateDTO> criaHospede(@Valid @RequestBody HospedeCreateDTO hospede) {

        HospedeCreateDTO hospedeCriado = hospedeService.criarHospede(hospede);
        return ResponseEntity.status(HttpStatus.CREATED).body(hospedeCriado);
    }


    @Operation(summary = "Cria endereco para hóspede", description = "Se o hóspede já tiver um endereço, o novo endereço será substituido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/endereco/{cpf}")
    public ResponseEntity<Endereco> criaEnderecoHospede(@PathVariable String cpf, @RequestBody Endereco endereco) {
        Endereco enderecoCriado = hospedeService.criaEnderecoParaHospede(cpf, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCriado);
    }


    @Operation(summary = "Pega endereço de um hóspede pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o CPF fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/endereco/{cpf}")
    public ResponseEntity<Endereco> getEnderecoHospedeByCPF(@PathVariable String cpf) {
        Endereco endereco = hospedeService.getEnderecoHospedeByCPF(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }


    @Operation(summary = "Retorna Hóspede pelo CPF.", description = "Apenas os atributos principais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hóspede encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<Hospede> getHospedeByCpf(@PathVariable String cpf) {
        Hospede hospede = hospedeService.getHospedeByCPF(cpf);

        if (hospede == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(hospede);
    }


    @PutMapping("/{cpf}")
    @Operation(summary = "Atualiza dados do hóspede.", description = "Atualiza atributos principais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hóspede atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<HospedeUpdateDTO> atualizaHospede(@PathVariable String cpf, @RequestBody HospedeUpdateDTO hospedeAtualizado) {
        HospedeUpdateDTO hospede = hospedeService.editaHospedeByCPF(cpf, hospedeAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(hospede);
    }


}

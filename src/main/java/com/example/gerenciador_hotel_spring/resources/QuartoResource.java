package com.example.gerenciador_hotel_spring.resources;

import com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO;
import com.example.gerenciador_hotel_spring.entities.Quarto;
import com.example.gerenciador_hotel_spring.enums.StatusQuarto;
import com.example.gerenciador_hotel_spring.enums.TipoQuarto;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.services.QuartoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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


    @Operation(summary = "Editar quarto por número", description = "Atualiza as informações de um quarto com base no número fornecido. Atualiza todos os campos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito: Quarto com o mesmo número já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{numero}")
    public ResponseEntity<Quarto> editaQuartoByNumero(@PathVariable String numero, @RequestBody Quarto quartoModificado) {
        Quarto quartoAtualizado = quartoService.editaQuartoByNumero(numero, quartoModificado);
        return ResponseEntity.status(HttpStatus.OK).body(quartoAtualizado);
    }


    @Operation(summary = "Busca paginada de quartos DTO por tipo", description = "Recupera quartos com base no tipo de quarto, paginados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quartos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum quarto encontrado para o status fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/por-tipo/{tipoQuarto}")
    public ResponseEntity<Page<QuartoResponseDTO>> buscarQuartosPorTipo(
            @PathVariable TipoQuarto tipoQuarto,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<QuartoResponseDTO> quartosDTO = quartoService.buscarQuartosDTOPorTipoPaginadas(tipoQuarto, pageable);

        if (quartosDTO.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum quarto encontrado para o tipo fornecidos.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(quartosDTO);
    }


    @Operation(summary = "Busca paginada de quartos DTO por tipo e status", description = "Recupera quartos com base no tipo e status, paginados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quartos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum quarto encontrado para o status fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/por-tipo/{tipoQuarto}/status/{statusQuarto}")
    public ResponseEntity<Page<QuartoResponseDTO>> buscarQuartosPorTipoEStatus(
            @PathVariable TipoQuarto tipoQuarto,
            @PathVariable StatusQuarto statusQuarto,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<QuartoResponseDTO> quartosDTO = quartoService.buscarQuartosDTOPorTipoEPorStatusPaginadas(tipoQuarto, statusQuarto, pageable);

        if (quartosDTO.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum quarto encontrado para o tipo e status fornecidos.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(quartosDTO);
    }


    @Operation(summary = "Busca paginada de quartos DTO por status", description = "Recupera quartos com base no status, paginados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quartos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum quarto encontrado para o status fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/por-status/{statusQuarto}")
    public ResponseEntity<Page<QuartoResponseDTO>> buscarQuartosPorStatus(
            @PathVariable StatusQuarto statusQuarto,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<QuartoResponseDTO> quartosDTO = quartoService.buscarQuartosDTOPorStatusPaginadas(statusQuarto, pageable);

        if (quartosDTO.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum quarto encontrado para o status fornecido.");
        }

        return ResponseEntity.ok(quartosDTO);
    }


    @Operation(summary = "Obter quartos DTO disponíveis por tipo e data",
            description = "Recupera uma lista paginada de quartos disponíveis com base no tipo de quarto e no intervalo de datas fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quartos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quartos não encontrados para os critérios fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/quartos/disponiveis")
    public ResponseEntity<Page<QuartoResponseDTO>> buscarQuartosDisponiveis(
            @RequestParam TipoQuarto tipoQuarto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataEntrada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataSaida,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<QuartoResponseDTO> quartosDisponiveis = quartoService.buscaQuartoPorTipoDisponiveisPorData(tipoQuarto, dataEntrada, dataSaida, pageable);

        if (quartosDisponiveis.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(quartosDisponiveis);
    }
}

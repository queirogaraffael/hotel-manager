package com.example.gerenciador.hotel.resources;



import com.example.gerenciador.hotel.domain.entities.Endereco;
import com.example.gerenciador.hotel.domain.entities.Funcionario;
import com.example.gerenciador.hotel.domain.services.FuncionarioService;
import com.example.gerenciador.hotel.shared.dtos.funcionario.FuncionarioCreateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioResource {

    @Autowired
    private FuncionarioService funcionarioService;


    @Operation(summary = "Cria novo funcionario", description = "FuncionarioDTO. Não adiciona com endereço nem extrato. CPF precisa ter exatamento 11 caracteres.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Conflito: Funcionário já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<FuncionarioCreateDTO> criaFuncionario(@Valid @RequestBody FuncionarioCreateDTO funcionario) {

        FuncionarioCreateDTO funcionarioCriado = funcionarioService.criarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCriado);
    }


    @Operation(summary = "Cria endereco", description = "Se o funcionário já tiver um endereço, o novo endereço será substituido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/endereco/{cpf}")
    public ResponseEntity<Endereco> criaEnderecoFuncionario(@PathVariable String cpf, @RequestBody Endereco endereco) {
        Endereco enderecoCriado = funcionarioService.criaEnderecoParaFuncionario(cpf, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCriado);
    }


    @Operation(summary = "Pega endereço de um funcionário pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o CPF fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/endereco/{cpf}")
    public ResponseEntity<Endereco> getEnderecoFuncionarioByCPF(@PathVariable String cpf) {
        Endereco endereco = funcionarioService.getEnderecoFuncionarioByCPF(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }


    @Operation(summary = "Retorna Funcionario pelo CPF.", description = "Apenas os atributos principais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<Funcionario> getFuncionarioByCpf(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioService.getFuncionarioByCPF(cpf);

        if (funcionario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(funcionario);
    }


    @GetMapping("/nome")
    @Operation(summary = "Busca funcionários DTO por nome de forma paginada.", description = "Retorna uma página de funcionários DTO cujo nome contém o valor fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionários encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<FuncionarioResponseDTO> buscaFuncionariosPorNome(
            @RequestParam String nome,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return funcionarioService.getFuncionariosDTOPorNomePaginados(nome, pageable);
    }


    @PutMapping("/{cpf}")
    @Operation(summary = "Atualiza dados do funcionario.", description = "Atributos principais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado com o CPF fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<FuncionarioUpdateDTO> atualizaFuncionario(@PathVariable String cpf, @RequestBody FuncionarioUpdateDTO funcionarioAtualizado) {
        FuncionarioUpdateDTO funcionario = funcionarioService.editaFuncionarioByCPF(cpf, funcionarioAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);
    }
}

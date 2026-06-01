package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.*;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.UserRegistrationDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.UserResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.UserWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final RegistrarUsuarioInputPort registrarUsuarioUseCase;
    private final BuscarPorUsernameInputPort buscarPorUsernameUseCase;
    private final UserWebMapper mapper;

    @Operation(summary = "Registrar novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Usuário já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/registrar")
    public ResponseEntity<UserResponseDTO> registrar(@Valid @RequestBody UserRegistrationDTO dto) {
        User user = registrarUsuarioUseCase.registrarUsuario(
                dto.getRole(), dto.getUsername(), dto.getSenha(), dto.getNome(),
                dto.getEmail(), dto.getTelefone(), dto.getCpf(), dto.getDataNascimento(),
                dto.getCargo(), dto.getTurno()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(user));
    }

    @Operation(summary = "Buscar usuário por username")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> buscar(@PathVariable String username) {
        return ResponseEntity.ok(mapper.toResponse(buscarPorUsernameUseCase.buscarPorUsername(username)));
    }
}

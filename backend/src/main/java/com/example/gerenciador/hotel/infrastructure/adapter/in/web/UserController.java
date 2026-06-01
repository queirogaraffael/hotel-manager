package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.*;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.LoginRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.LoginResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.UserRegistrationDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.UserResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper.UserWebMapper;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.user.UserJpaRepository;
import com.example.gerenciador.hotel.infrastructure.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final RegistrarUsuarioInputPort registrarUsuarioUseCase;
    private final BuscarPorUsernameInputPort buscarPorUsernameUseCase;
    private final UserWebMapper mapper;
    private final TokenService tokenService;
    private final UserJpaRepository userJpaRepository; // Necessário para UserDetails até mover Spring Security p/ infra

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

    @Operation(summary = "Login — retorna JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        // UserDetails com getAuthorities() vive na UserEntity (infra); buscamos direto no JPA repo
        UserDetails userDetails = userJpaRepository.findByUsername(dto.getUsername());
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Gera token via TokenService (que aceita UserDetails via username)
        String token = tokenService.generateTokenFromUsername(dto.getUsername());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Buscar usuário por username")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> buscar(@PathVariable String username) {
        return ResponseEntity.ok(mapper.toResponse(buscarPorUsernameUseCase.buscarPorUsername(username)));
    }
}

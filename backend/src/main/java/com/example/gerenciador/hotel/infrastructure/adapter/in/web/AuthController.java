package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.domain.port.in.user.AutenticarInputPort;
import com.example.gerenciador.hotel.domain.port.in.user.BuscarUserPorIdInputPort;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.LoginRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.LoginResponseDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.UserContextDTO;
import com.example.gerenciador.hotel.infrastructure.security.AuthenticatedUser;
import com.example.gerenciador.hotel.infrastructure.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AutenticarInputPort autenticarUseCase;
    private final BuscarUserPorIdInputPort buscarUserPorIdUseCase;
    private final TokenService tokenService;

    public AuthController(
            AutenticarInputPort autenticarUseCase,
            BuscarUserPorIdInputPort buscarUserPorIdUseCase,
            TokenService tokenService) {
        this.autenticarUseCase = autenticarUseCase;
        this.buscarUserPorIdUseCase = buscarUserPorIdUseCase;
        this.tokenService = tokenService;
    }

    @Operation(summary = "Login — retorna JWT")
    @ApiResponse(responseCode = "200", description = "Login bem-sucedido")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        User user = autenticarUseCase.autenticar(dto.getUsername(), dto.getSenha());
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(
            summary = "Obter contexto do usuário autenticado",
            description = "Retorna informações sobre o usuário atualmente logado."
    )
    @ApiResponse(responseCode = "200", description = "Contexto retornado com sucesso")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    @GetMapping("/me")
    public ResponseEntity<UserContextDTO> getUserContext(@AuthenticationPrincipal AuthenticatedUser principal) {
        User user = buscarUserPorIdUseCase.buscarPorId(principal.getId());
        return ResponseEntity.ok(new UserContextDTO(
                user.getId(),
                user.getNome(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole()
        ));
    }
}

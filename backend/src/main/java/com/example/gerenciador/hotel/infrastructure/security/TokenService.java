package com.example.gerenciador.hotel.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.gerenciador.hotel.domain.enums.UserRole;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.security.exception.TokenCreationException;
import com.example.gerenciador.hotel.infrastructure.security.exception.TokenValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration}")
    private Long expirationHours;

    @Value("${api.security.token.issuer}")
    private String issuer;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            List<String> authorities = buildAuthorities(user.getUserRole());

            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getId().toString())
                    .withClaim("roles", authorities)
                    .withIssuedAt(new Date())
                    .withExpiresAt(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new TokenCreationException("Erro ao gerar o token JWT", e);
        }
    }

    public AuthenticatedUser validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decoded = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);

            UUID userId = UUID.fromString(decoded.getSubject());
            List<String> roles = decoded.getClaim("roles").asList(String.class);
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            return new AuthenticatedUser(userId, authorities);
        } catch (JWTVerificationException e) {
            throw new TokenValidationException("Token inválido ou expirado", e);
        }
    }

    private List<String> buildAuthorities(UserRole userRole) {
        List<String> authorities = new ArrayList<>();
        authorities.add("ROLE_" + userRole.name());
        switch (userRole) {
            case ADMIN -> {
                authorities.add("ROLE_FUNCIONARIO");
                authorities.add("ROLE_HOSPEDE");
            }
            case FUNCIONARIO -> authorities.add("ROLE_HOSPEDE");
            default -> {}
        }
        return authorities;
    }
}
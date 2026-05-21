package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.user.UserResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class UserWebMapperImpl implements UserWebMapper {

    @Override
    public UserResponseDTO toResponse(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( domain.getId() );
        userResponseDTO.setUsername( domain.getUsername() );
        userResponseDTO.setCpf( domain.getCpf() );
        userResponseDTO.setDataNascimento( domain.getDataNascimento() );
        userResponseDTO.setNome( domain.getNome() );
        userResponseDTO.setEmail( domain.getEmail() );
        userResponseDTO.setTelefone( domain.getTelefone() );
        userResponseDTO.setUserRole( domain.getUserRole() );

        return userResponseDTO;
    }
}

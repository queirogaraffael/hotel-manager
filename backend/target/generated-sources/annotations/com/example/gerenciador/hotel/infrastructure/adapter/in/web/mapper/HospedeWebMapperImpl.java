package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.hospede.HospedeResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class HospedeWebMapperImpl implements HospedeWebMapper {

    @Override
    public HospedeResponseDTO toResponse(Hospede domain) {
        if ( domain == null ) {
            return null;
        }

        HospedeResponseDTO hospedeResponseDTO = new HospedeResponseDTO();

        hospedeResponseDTO.setCpf( domainUserCpf( domain ) );
        hospedeResponseDTO.setNome( domainUserNome( domain ) );
        hospedeResponseDTO.setEmail( domainUserEmail( domain ) );
        hospedeResponseDTO.setTelefone( domainUserTelefone( domain ) );
        hospedeResponseDTO.setId( domain.getId() );

        return hospedeResponseDTO;
    }

    private String domainUserCpf(Hospede hospede) {
        if ( hospede == null ) {
            return null;
        }
        User user = hospede.getUser();
        if ( user == null ) {
            return null;
        }
        String cpf = user.getCpf();
        if ( cpf == null ) {
            return null;
        }
        return cpf;
    }

    private String domainUserNome(Hospede hospede) {
        if ( hospede == null ) {
            return null;
        }
        User user = hospede.getUser();
        if ( user == null ) {
            return null;
        }
        String nome = user.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private String domainUserEmail(Hospede hospede) {
        if ( hospede == null ) {
            return null;
        }
        User user = hospede.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String domainUserTelefone(Hospede hospede) {
        if ( hospede == null ) {
            return null;
        }
        User user = hospede.getUser();
        if ( user == null ) {
            return null;
        }
        String telefone = user.getTelefone();
        if ( telefone == null ) {
            return null;
        }
        return telefone;
    }
}

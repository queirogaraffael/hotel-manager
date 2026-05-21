package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.funcionario.FuncionarioResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class FuncionarioWebMapperImpl implements FuncionarioWebMapper {

    @Override
    public FuncionarioResponseDTO toResponse(Funcionario domain) {
        if ( domain == null ) {
            return null;
        }

        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO();

        funcionarioResponseDTO.setCpf( domainUserCpf( domain ) );
        funcionarioResponseDTO.setNome( domainUserNome( domain ) );
        funcionarioResponseDTO.setId( domain.getId() );
        funcionarioResponseDTO.setCargo( domain.getCargo() );
        funcionarioResponseDTO.setTurno( domain.getTurno() );

        return funcionarioResponseDTO;
    }

    private String domainUserCpf(Funcionario funcionario) {
        if ( funcionario == null ) {
            return null;
        }
        User user = funcionario.getUser();
        if ( user == null ) {
            return null;
        }
        String cpf = user.getCpf();
        if ( cpf == null ) {
            return null;
        }
        return cpf;
    }

    private String domainUserNome(Funcionario funcionario) {
        if ( funcionario == null ) {
            return null;
        }
        User user = funcionario.getUser();
        if ( user == null ) {
            return null;
        }
        String nome = user.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }
}

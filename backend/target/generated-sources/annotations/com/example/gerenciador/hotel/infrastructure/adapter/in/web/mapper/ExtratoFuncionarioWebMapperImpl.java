package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.extratofuncionario.ExtratoFuncionarioResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class ExtratoFuncionarioWebMapperImpl implements ExtratoFuncionarioWebMapper {

    @Override
    public ExtratoFuncionarioResponseDTO toResponse(ExtratoFuncionario domain) {
        if ( domain == null ) {
            return null;
        }

        ExtratoFuncionarioResponseDTO extratoFuncionarioResponseDTO = new ExtratoFuncionarioResponseDTO();

        extratoFuncionarioResponseDTO.setId( domain.getId() );
        extratoFuncionarioResponseDTO.setDataExtrato( domain.getDataExtrato() );
        extratoFuncionarioResponseDTO.setHorasTrabalhadas( domain.getHorasTrabalhadas() );
        extratoFuncionarioResponseDTO.setValorHora( domain.getValorHora() );
        extratoFuncionarioResponseDTO.setSalario( domain.getSalario() );

        return extratoFuncionarioResponseDTO;
    }
}

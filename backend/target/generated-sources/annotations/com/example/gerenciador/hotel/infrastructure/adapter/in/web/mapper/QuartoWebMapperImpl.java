package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.quarto.QuartoResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class QuartoWebMapperImpl implements QuartoWebMapper {

    @Override
    public QuartoResponseDTO toResponse(Quarto domain) {
        if ( domain == null ) {
            return null;
        }

        QuartoResponseDTO quartoResponseDTO = new QuartoResponseDTO();

        quartoResponseDTO.setId( domain.getId() );
        quartoResponseDTO.setNumero( domain.getNumero() );
        quartoResponseDTO.setTipoQuarto( domain.getTipoQuarto() );
        quartoResponseDTO.setCapacidade( domain.getCapacidade() );
        quartoResponseDTO.setPrecoDiaria( domain.getPrecoDiaria() );
        quartoResponseDTO.setStatusQuarto( domain.getStatusQuarto() );

        return quartoResponseDTO;
    }
}

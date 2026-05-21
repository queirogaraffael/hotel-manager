package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoRequestDTO;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.endereco.EnderecoResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class EnderecoWebMapperImpl implements EnderecoWebMapper {

    @Override
    public Endereco toDomain(EnderecoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco.EnderecoBuilder endereco = Endereco.builder();

        endereco.rua( dto.getRua() );
        endereco.numero( dto.getNumero() );
        endereco.bairro( dto.getBairro() );
        endereco.cidade( dto.getCidade() );
        endereco.estado( dto.getEstado() );
        endereco.cep( dto.getCep() );

        return endereco.build();
    }

    @Override
    public EnderecoResponseDTO toResponse(Endereco domain) {
        if ( domain == null ) {
            return null;
        }

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();

        enderecoResponseDTO.setId( domain.getId() );
        enderecoResponseDTO.setRua( domain.getRua() );
        enderecoResponseDTO.setNumero( domain.getNumero() );
        enderecoResponseDTO.setBairro( domain.getBairro() );
        enderecoResponseDTO.setCidade( domain.getCidade() );
        enderecoResponseDTO.setEstado( domain.getEstado() );
        enderecoResponseDTO.setCep( domain.getCep() );

        return enderecoResponseDTO;
    }
}

package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.EnderecoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class EnderecoEntityMapperImpl implements EnderecoEntityMapper {

    @Override
    public EnderecoEntity toEntity(Endereco domain) {
        if ( domain == null ) {
            return null;
        }

        EnderecoEntity enderecoEntity = new EnderecoEntity();

        enderecoEntity.setId( domain.getId() );
        enderecoEntity.setRua( domain.getRua() );
        enderecoEntity.setNumero( domain.getNumero() );
        enderecoEntity.setBairro( domain.getBairro() );
        enderecoEntity.setCidade( domain.getCidade() );
        enderecoEntity.setEstado( domain.getEstado() );
        enderecoEntity.setCep( domain.getCep() );

        return enderecoEntity;
    }

    @Override
    public Endereco toDomain(EnderecoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Endereco.EnderecoBuilder endereco = Endereco.builder();

        endereco.id( entity.getId() );
        endereco.rua( entity.getRua() );
        endereco.numero( entity.getNumero() );
        endereco.bairro( entity.getBairro() );
        endereco.cidade( entity.getCidade() );
        endereco.estado( entity.getEstado() );
        endereco.cep( entity.getCep() );

        return endereco.build();
    }
}

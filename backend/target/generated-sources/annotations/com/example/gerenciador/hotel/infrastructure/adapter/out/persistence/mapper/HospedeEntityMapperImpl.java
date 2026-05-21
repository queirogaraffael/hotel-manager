package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.HospedeEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class HospedeEntityMapperImpl implements HospedeEntityMapper {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Override
    public HospedeEntity toEntity(Hospede domain) {
        if ( domain == null ) {
            return null;
        }

        HospedeEntity hospedeEntity = new HospedeEntity();

        hospedeEntity.setId( domain.getId() );
        hospedeEntity.setUser( userEntityMapper.toEntity( domain.getUser() ) );

        return hospedeEntity;
    }

    @Override
    public Hospede toDomain(HospedeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Hospede.HospedeBuilder hospede = Hospede.builder();

        hospede.id( entity.getId() );
        hospede.user( userEntityMapper.toDomain( entity.getUser() ) );

        return hospede.build();
    }
}

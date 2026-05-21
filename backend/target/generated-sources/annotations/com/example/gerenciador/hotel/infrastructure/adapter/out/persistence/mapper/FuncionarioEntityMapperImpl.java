package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.ExtratoFuncionarioEntity;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.FuncionarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class FuncionarioEntityMapperImpl implements FuncionarioEntityMapper {

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private ExtratoFuncionarioEntityMapper extratoFuncionarioEntityMapper;

    @Override
    public FuncionarioEntity toEntity(Funcionario domain) {
        if ( domain == null ) {
            return null;
        }

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();

        funcionarioEntity.setId( domain.getId() );
        funcionarioEntity.setCargo( domain.getCargo() );
        funcionarioEntity.setTurno( domain.getTurno() );
        funcionarioEntity.setUser( userEntityMapper.toEntity( domain.getUser() ) );
        funcionarioEntity.setExtratoFuncionario( extratoFuncionarioListToExtratoFuncionarioEntityList( domain.getExtratoFuncionario() ) );

        return funcionarioEntity;
    }

    @Override
    public Funcionario toDomain(FuncionarioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Funcionario.FuncionarioBuilder funcionario = Funcionario.builder();

        funcionario.id( entity.getId() );
        funcionario.cargo( entity.getCargo() );
        funcionario.turno( entity.getTurno() );
        funcionario.user( userEntityMapper.toDomain( entity.getUser() ) );

        return funcionario.build();
    }

    protected List<ExtratoFuncionarioEntity> extratoFuncionarioListToExtratoFuncionarioEntityList(List<ExtratoFuncionario> list) {
        if ( list == null ) {
            return null;
        }

        List<ExtratoFuncionarioEntity> list1 = new ArrayList<ExtratoFuncionarioEntity>( list.size() );
        for ( ExtratoFuncionario extratoFuncionario : list ) {
            list1.add( extratoFuncionarioEntityMapper.toEntity( extratoFuncionario ) );
        }

        return list1;
    }
}

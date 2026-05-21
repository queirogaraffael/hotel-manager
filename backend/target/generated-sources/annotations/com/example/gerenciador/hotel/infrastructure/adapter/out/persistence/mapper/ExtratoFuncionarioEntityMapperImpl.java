package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.ExtratoFuncionario;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.ExtratoFuncionarioEntity;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.FuncionarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class ExtratoFuncionarioEntityMapperImpl implements ExtratoFuncionarioEntityMapper {

    @Override
    public ExtratoFuncionarioEntity toEntity(ExtratoFuncionario domain) {
        if ( domain == null ) {
            return null;
        }

        ExtratoFuncionarioEntity extratoFuncionarioEntity = new ExtratoFuncionarioEntity();

        extratoFuncionarioEntity.setFuncionario( extratoFuncionarioToFuncionarioEntity( domain ) );
        extratoFuncionarioEntity.setId( domain.getId() );
        extratoFuncionarioEntity.setDataExtrato( domain.getDataExtrato() );
        extratoFuncionarioEntity.setHorasTrabalhadas( domain.getHorasTrabalhadas() );
        extratoFuncionarioEntity.setValorHora( domain.getValorHora() );
        extratoFuncionarioEntity.setSalario( domain.getSalario() );

        return extratoFuncionarioEntity;
    }

    @Override
    public ExtratoFuncionario toDomain(ExtratoFuncionarioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ExtratoFuncionario.ExtratoFuncionarioBuilder extratoFuncionario = ExtratoFuncionario.builder();

        extratoFuncionario.funcionarioId( entityFuncionarioId( entity ) );
        extratoFuncionario.id( entity.getId() );
        extratoFuncionario.dataExtrato( entity.getDataExtrato() );
        extratoFuncionario.horasTrabalhadas( entity.getHorasTrabalhadas() );
        extratoFuncionario.valorHora( entity.getValorHora() );
        extratoFuncionario.salario( entity.getSalario() );

        return extratoFuncionario.build();
    }

    protected FuncionarioEntity extratoFuncionarioToFuncionarioEntity(ExtratoFuncionario extratoFuncionario) {
        if ( extratoFuncionario == null ) {
            return null;
        }

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();

        funcionarioEntity.setId( extratoFuncionario.getFuncionarioId() );

        return funcionarioEntity;
    }

    private Long entityFuncionarioId(ExtratoFuncionarioEntity extratoFuncionarioEntity) {
        if ( extratoFuncionarioEntity == null ) {
            return null;
        }
        FuncionarioEntity funcionario = extratoFuncionarioEntity.getFuncionario();
        if ( funcionario == null ) {
            return null;
        }
        Long id = funcionario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

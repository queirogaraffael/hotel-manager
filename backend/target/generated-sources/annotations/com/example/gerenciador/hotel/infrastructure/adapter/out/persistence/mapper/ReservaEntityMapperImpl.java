package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.ReservaEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class ReservaEntityMapperImpl implements ReservaEntityMapper {

    @Autowired
    private QuartoEntityMapper quartoEntityMapper;
    @Autowired
    private HospedeEntityMapper hospedeEntityMapper;

    @Override
    public ReservaEntity toEntity(Reserva domain) {
        if ( domain == null ) {
            return null;
        }

        ReservaEntity reservaEntity = new ReservaEntity();

        reservaEntity.setId( domain.getId() );
        reservaEntity.setDataEntrada( domain.getDataEntrada() );
        reservaEntity.setDataSaida( domain.getDataSaida() );
        reservaEntity.setNumeroHospedes( domain.getNumeroHospedes() );
        reservaEntity.setStatusReserva( domain.getStatusReserva() );
        reservaEntity.setValorTotal( domain.getValorTotal() );
        reservaEntity.setQuarto( quartoEntityMapper.toEntity( domain.getQuarto() ) );
        reservaEntity.setHospede( hospedeEntityMapper.toEntity( domain.getHospede() ) );

        return reservaEntity;
    }

    @Override
    public Reserva toDomain(ReservaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Reserva.ReservaBuilder reserva = Reserva.builder();

        reserva.id( entity.getId() );
        reserva.dataEntrada( entity.getDataEntrada() );
        reserva.dataSaida( entity.getDataSaida() );
        reserva.numeroHospedes( entity.getNumeroHospedes() );
        reserva.statusReserva( entity.getStatusReserva() );
        reserva.valorTotal( entity.getValorTotal() );
        reserva.quarto( quartoEntityMapper.toDomain( entity.getQuarto() ) );
        reserva.hospede( hospedeEntityMapper.toDomain( entity.getHospede() ) );

        return reserva.build();
    }
}

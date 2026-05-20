package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.ReservaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {QuartoEntityMapper.class, HospedeEntityMapper.class})
public interface ReservaEntityMapper {

    ReservaEntity toEntity(Reserva domain);

    Reserva toDomain(ReservaEntity entity);
}

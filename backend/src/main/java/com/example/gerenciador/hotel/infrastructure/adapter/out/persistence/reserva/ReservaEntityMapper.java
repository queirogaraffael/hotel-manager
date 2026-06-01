package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.hospede.HospedeEntityMapper;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.quarto.QuartoEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {QuartoEntityMapper.class, HospedeEntityMapper.class})
public interface ReservaEntityMapper {

    ReservaEntity toEntity(Reserva domain);

    Reserva toDomain(ReservaEntity entity);
}

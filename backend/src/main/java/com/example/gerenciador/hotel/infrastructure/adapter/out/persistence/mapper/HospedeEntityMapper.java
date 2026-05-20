package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.HospedeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserEntityMapper.class, ReservaEntityMapper.class})
public interface HospedeEntityMapper {

    HospedeEntity toEntity(Hospede domain);

    // Ignora a lista de reservas no mapeamento inverso para evitar ciclo Hospede→Reserva→Hospede
    @Mapping(target = "reservas", ignore = true)
    Hospede toDomain(HospedeEntity entity);
}

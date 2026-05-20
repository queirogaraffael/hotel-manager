package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.QuartoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuartoEntityMapper {

    QuartoEntity toEntity(Quarto domain);

    // Ignora a lista de reservas para evitar ciclo Quarto→Reserva→Quarto
    @Mapping(target = "reservas", ignore = true)
    Quarto toDomain(QuartoEntity entity);
}

package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.hospede;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.user.UserEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserEntityMapper.class})
public interface HospedeEntityMapper {

    @Mapping(target = "reservas", ignore = true)
    HospedeEntity toEntity(Hospede domain);

    Hospede toDomain(HospedeEntity entity);
}

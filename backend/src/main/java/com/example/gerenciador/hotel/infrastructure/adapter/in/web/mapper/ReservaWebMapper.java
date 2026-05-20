package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.reserva.ReservaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaWebMapper {

    @Mapping(target = "numeroQuarto", source = "quarto.numero")
    @Mapping(target = "cpfHospede", source = "hospede.user.cpf")
    ReservaResponseDTO toResponse(Reserva domain);
}

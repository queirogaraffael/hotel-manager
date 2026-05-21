package com.example.gerenciador.hotel.infrastructure.adapter.in.web.mapper;

import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.reserva.ReservaResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class ReservaWebMapperImpl implements ReservaWebMapper {

    @Override
    public ReservaResponseDTO toResponse(Reserva domain) {
        if ( domain == null ) {
            return null;
        }

        ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();

        reservaResponseDTO.setNumeroQuarto( domainQuartoNumero( domain ) );
        reservaResponseDTO.setCpfHospede( domainHospedeUserCpf( domain ) );
        reservaResponseDTO.setId( domain.getId() );
        reservaResponseDTO.setDataEntrada( domain.getDataEntrada() );
        reservaResponseDTO.setDataSaida( domain.getDataSaida() );
        reservaResponseDTO.setNumeroHospedes( domain.getNumeroHospedes() );
        reservaResponseDTO.setStatusReserva( domain.getStatusReserva() );
        reservaResponseDTO.setValorTotal( domain.getValorTotal() );

        return reservaResponseDTO;
    }

    private String domainQuartoNumero(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }
        Quarto quarto = reserva.getQuarto();
        if ( quarto == null ) {
            return null;
        }
        String numero = quarto.getNumero();
        if ( numero == null ) {
            return null;
        }
        return numero;
    }

    private String domainHospedeUserCpf(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }
        Hospede hospede = reserva.getHospede();
        if ( hospede == null ) {
            return null;
        }
        User user = hospede.getUser();
        if ( user == null ) {
            return null;
        }
        String cpf = user.getCpf();
        if ( cpf == null ) {
            return null;
        }
        return cpf;
    }
}

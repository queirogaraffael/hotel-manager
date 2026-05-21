package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Hospede;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.model.Reserva;
import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.EnderecoEntity;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.HospedeEntity;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.QuartoEntity;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.ReservaEntity;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class QuartoEntityMapperImpl implements QuartoEntityMapper {

    @Override
    public QuartoEntity toEntity(Quarto domain) {
        if ( domain == null ) {
            return null;
        }

        QuartoEntity.QuartoEntityBuilder quartoEntity = QuartoEntity.builder();

        quartoEntity.id( domain.getId() );
        quartoEntity.numero( domain.getNumero() );
        quartoEntity.tipoQuarto( domain.getTipoQuarto() );
        quartoEntity.capacidade( domain.getCapacidade() );
        quartoEntity.precoDiaria( domain.getPrecoDiaria() );
        quartoEntity.statusQuarto( domain.getStatusQuarto() );
        quartoEntity.reservas( reservaListToReservaEntityList( domain.getReservas() ) );

        return quartoEntity.build();
    }

    @Override
    public Quarto toDomain(QuartoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Quarto.QuartoBuilder quarto = Quarto.builder();

        quarto.id( entity.getId() );
        quarto.numero( entity.getNumero() );
        quarto.tipoQuarto( entity.getTipoQuarto() );
        quarto.capacidade( entity.getCapacidade() );
        quarto.precoDiaria( entity.getPrecoDiaria() );
        quarto.statusQuarto( entity.getStatusQuarto() );

        return quarto.build();
    }

    protected EnderecoEntity enderecoToEnderecoEntity(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoEntity enderecoEntity = new EnderecoEntity();

        enderecoEntity.setId( endereco.getId() );
        enderecoEntity.setRua( endereco.getRua() );
        enderecoEntity.setNumero( endereco.getNumero() );
        enderecoEntity.setBairro( endereco.getBairro() );
        enderecoEntity.setCidade( endereco.getCidade() );
        enderecoEntity.setEstado( endereco.getEstado() );
        enderecoEntity.setCep( endereco.getCep() );

        return enderecoEntity;
    }

    protected UserEntity userToUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setUsername( user.getUsername() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setCpf( user.getCpf() );
        userEntity.setDataNascimento( user.getDataNascimento() );
        userEntity.setNome( user.getNome() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setTelefone( user.getTelefone() );
        userEntity.setUserRole( user.getUserRole() );
        userEntity.setEndereco( enderecoToEnderecoEntity( user.getEndereco() ) );

        return userEntity;
    }

    protected HospedeEntity hospedeToHospedeEntity(Hospede hospede) {
        if ( hospede == null ) {
            return null;
        }

        HospedeEntity hospedeEntity = new HospedeEntity();

        hospedeEntity.setId( hospede.getId() );
        hospedeEntity.setUser( userToUserEntity( hospede.getUser() ) );

        return hospedeEntity;
    }

    protected ReservaEntity reservaToReservaEntity(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }

        ReservaEntity reservaEntity = new ReservaEntity();

        reservaEntity.setId( reserva.getId() );
        reservaEntity.setDataEntrada( reserva.getDataEntrada() );
        reservaEntity.setDataSaida( reserva.getDataSaida() );
        reservaEntity.setNumeroHospedes( reserva.getNumeroHospedes() );
        reservaEntity.setStatusReserva( reserva.getStatusReserva() );
        reservaEntity.setValorTotal( reserva.getValorTotal() );
        reservaEntity.setQuarto( toEntity( reserva.getQuarto() ) );
        reservaEntity.setHospede( hospedeToHospedeEntity( reserva.getHospede() ) );

        return reservaEntity;
    }

    protected List<ReservaEntity> reservaListToReservaEntityList(List<Reserva> list) {
        if ( list == null ) {
            return null;
        }

        List<ReservaEntity> list1 = new ArrayList<ReservaEntity>( list.size() );
        for ( Reserva reserva : list ) {
            list1.add( reservaToReservaEntity( reserva ) );
        }

        return list1;
    }
}

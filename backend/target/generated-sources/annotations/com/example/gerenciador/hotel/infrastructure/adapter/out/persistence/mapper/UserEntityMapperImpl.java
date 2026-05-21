package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.mapper;

import com.example.gerenciador.hotel.domain.model.User;
import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T12:56:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Azul Systems, Inc.)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Autowired
    private EnderecoEntityMapper enderecoEntityMapper;

    @Override
    public UserEntity toEntity(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( domain.getId() );
        userEntity.setUsername( domain.getUsername() );
        userEntity.setPassword( domain.getPassword() );
        userEntity.setCpf( domain.getCpf() );
        userEntity.setDataNascimento( domain.getDataNascimento() );
        userEntity.setNome( domain.getNome() );
        userEntity.setEmail( domain.getEmail() );
        userEntity.setTelefone( domain.getTelefone() );
        userEntity.setUserRole( domain.getUserRole() );
        userEntity.setEndereco( enderecoEntityMapper.toEntity( domain.getEndereco() ) );

        return userEntity;
    }

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( entity.getId() );
        user.username( entity.getUsername() );
        user.password( entity.getPassword() );
        user.cpf( entity.getCpf() );
        user.dataNascimento( entity.getDataNascimento() );
        user.nome( entity.getNome() );
        user.email( entity.getEmail() );
        user.telefone( entity.getTelefone() );
        user.userRole( entity.getUserRole() );
        user.endereco( enderecoEntityMapper.toDomain( entity.getEndereco() ) );

        return user.build();
    }
}

package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.endereco;

import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
    private UserEntity user;
}

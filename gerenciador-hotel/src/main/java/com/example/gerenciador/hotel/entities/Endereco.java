package com.example.gerenciador.hotel.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}

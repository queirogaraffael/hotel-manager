package com.example.gerenciador_hotel_spring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String estado;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
    private Pessoa pessoa;
}
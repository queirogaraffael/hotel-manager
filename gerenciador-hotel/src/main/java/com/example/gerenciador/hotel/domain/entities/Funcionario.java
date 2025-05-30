package com.example.gerenciador.hotel.domain.entities;


import com.example.gerenciador.hotel.domain.enums.Turno;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExtratoFuncionario> extratoFuncionario = new ArrayList<>();
}


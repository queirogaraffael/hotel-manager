package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity;

import com.example.gerenciador.hotel.domain.enums.Turno;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @NotNull
    private UserEntity user;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExtratoFuncionarioEntity> extratoFuncionario = new ArrayList<>();
}

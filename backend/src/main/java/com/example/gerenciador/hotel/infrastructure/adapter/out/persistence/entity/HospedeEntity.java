package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospede")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospedeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @OneToMany(mappedBy = "hospede", cascade = CascadeType.ALL)
    private List<ReservaEntity> reservas = new ArrayList<>();
}

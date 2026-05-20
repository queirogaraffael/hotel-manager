package com.example.gerenciador.hotel.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hospede {

    private Long id;
    private User user;

    @Builder.Default
    private List<Reserva> reservas = new ArrayList<>();
}

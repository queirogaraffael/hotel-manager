package com.example.gerenciador.hotel.domain.port.in.reserva;

import com.example.gerenciador.hotel.domain.model.Reserva;
import java.time.LocalDateTime;

public interface CriarReservaUseCase {
    Reserva criarReserva(String cpfHospede, String numeroQuarto, LocalDateTime dataEntrada, LocalDateTime dataSaida, Integer numeroHospedes);
}

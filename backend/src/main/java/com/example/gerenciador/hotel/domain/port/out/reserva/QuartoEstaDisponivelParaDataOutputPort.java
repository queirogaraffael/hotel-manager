package com.example.gerenciador.hotel.domain.port.out.reserva;

import java.time.LocalDateTime;

public interface QuartoEstaDisponivelParaDataOutputPort {
    boolean quartoEstaDisponivelParaData(LocalDateTime dataEntrada, LocalDateTime dataSaida, String numeroQuarto);
}

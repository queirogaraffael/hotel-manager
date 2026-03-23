package com.example.gerenciador.hotel.shared.dtos.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaUpdateDTO {

    @FutureOrPresent(message = "A data de entrada não pode ser no passado")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataEntrada;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataSaida;

    @Min(value = 1, message = "O número de hóspedes deve ser no mínimo 1")
    private Integer numeroHospedes;

    private StatusReserva statusReserva;

}
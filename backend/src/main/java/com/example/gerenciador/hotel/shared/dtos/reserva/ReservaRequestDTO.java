package com.example.gerenciador.hotel.shared.dtos.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequestDTO {

    @NotNull(message = "A data de entrada é obrigatória")
    @FutureOrPresent(message = "A data de entrada não pode ser no passado")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataEntrada;

    @NotNull(message = "A data de saída é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataSaida;

    @Min(value = 1, message = "O número de hóspedes deve ser no mínimo 1")
    @NotNull(message = "O número de hóspedes é obrigatório")
    private Integer numeroHospedes;

    private StatusReserva statusReserva;

    @NotNull(message = "O ID do quarto é obrigatório")
    private Long quartoId;

    @NotNull(message = "O ID do hóspede é obrigatório")
    private Long hospedeId;
}
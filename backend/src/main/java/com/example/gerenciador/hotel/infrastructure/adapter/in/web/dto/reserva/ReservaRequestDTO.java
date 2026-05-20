package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequestDTO {

    @NotBlank(message = "O número do quarto é obrigatório")
    private String numeroQuarto;

    @NotNull(message = "A data de entrada é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataEntrada;

    @NotNull(message = "A data de saída é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataSaida;

    @Min(value = 1, message = "O número de hóspedes deve ser no mínimo 1")
    @NotNull(message = "O número de hóspedes é obrigatório")
    private Integer numeroHospedes;
}

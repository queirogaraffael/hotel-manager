package com.example.gerenciador.hotel.shared.dtos.hospede;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospedeRequestDTO {

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Long userId;
}
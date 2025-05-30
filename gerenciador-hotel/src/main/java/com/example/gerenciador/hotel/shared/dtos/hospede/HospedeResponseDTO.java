package com.example.gerenciador.hotel.shared.dtos.hospede;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospedeResponseDTO {
    private Long id;
    private Long userId;
}
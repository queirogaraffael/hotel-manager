package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.hospede;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospedeResponseDTO {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
}

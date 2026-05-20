package com.example.gerenciador.hotel.infrastructure.adapter.in.web.dto.funcionario;

import com.example.gerenciador.hotel.domain.enums.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResponseDTO {
    private Long id;
    private String cargo;
    private Turno turno;
    private String cpf;
    private String nome;
}

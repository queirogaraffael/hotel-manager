package com.example.gerenciador.hotel.shared.dtos.funcionario;


import com.example.gerenciador.hotel.domain.enums.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioUpdateDTO {
    private String cargo;
    private Turno turno;
}
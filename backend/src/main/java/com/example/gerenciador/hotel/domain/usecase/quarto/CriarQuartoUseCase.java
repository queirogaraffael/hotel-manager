package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.CriarQuartoInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.ExistsQuartoByNumeroOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.SaveQuartoOutputPort;
import com.example.gerenciador.hotel.shared.exception.QuartoJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarQuartoUseCase implements CriarQuartoInputPort {

    private final ExistsQuartoByNumeroOutputPort existsQuartoByNumeroOutputPort;
    private final SaveQuartoOutputPort saveQuartoOutputPort;

    @Override
    @Transactional
    public Quarto criarQuarto(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria, StatusQuarto statusQuarto) {
        if (existsQuartoByNumeroOutputPort.existsByNumero(numero)) {
            throw new QuartoJaExisteException("Quarto já cadastrado com esse número.");
        }
        Quarto quarto = Quarto.builder()
                .numero(numero)
                .tipoQuarto(tipoQuarto)
                .capacidade(capacidade)
                .precoDiaria(precoDiaria)
                .statusQuarto(statusQuarto)
                .build();
        return saveQuartoOutputPort.save(quarto);
    }
}

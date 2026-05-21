package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartoPorNumeroInputPort;
import com.example.gerenciador.hotel.domain.port.in.quarto.ModificarStatusQuartoInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.SaveQuartoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModificarStatusQuartoUseCase implements ModificarStatusQuartoInputPort {

    private final BuscarQuartoPorNumeroInputPort buscarQuartoPorNumeroInputPort;
    private final SaveQuartoOutputPort saveQuartoOutputPort;

    @Override
    @Transactional
    public Quarto modificarStatusQuarto(String numero, StatusQuarto statusQuarto) {
        Quarto quarto = buscarQuartoPorNumeroInputPort.buscarQuartoPorNumero(numero);
        quarto.setStatusQuarto(statusQuarto);
        return saveQuartoOutputPort.save(quarto);
    }
}

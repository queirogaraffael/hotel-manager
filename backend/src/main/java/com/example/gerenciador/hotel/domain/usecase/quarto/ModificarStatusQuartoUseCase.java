package com.example.gerenciador.hotel.domain.usecase.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartoPorNumeroInputPort;
import com.example.gerenciador.hotel.domain.port.in.quarto.ModificarStatusQuartoInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.SaveQuartoOutputPort;
import org.springframework.transaction.annotation.Transactional;

public class ModificarStatusQuartoUseCase implements ModificarStatusQuartoInputPort {

    private final BuscarQuartoPorNumeroInputPort buscarQuartoPorNumeroInputPort;
    private final SaveQuartoOutputPort saveQuartoOutputPort;

    public ModificarStatusQuartoUseCase(BuscarQuartoPorNumeroInputPort buscarQuartoPorNumeroInputPort, SaveQuartoOutputPort saveQuartoOutputPort) {
        this.buscarQuartoPorNumeroInputPort = buscarQuartoPorNumeroInputPort;
        this.saveQuartoOutputPort = saveQuartoOutputPort;
    }

    @Override
    @Transactional
    public Quarto modificarStatusQuarto(String numero, StatusQuarto statusQuarto) {
        Quarto quarto = buscarQuartoPorNumeroInputPort.buscarQuartoPorNumero(numero);
        quarto.setStatusQuarto(statusQuarto);
        return saveQuartoOutputPort.save(quarto);
    }
}

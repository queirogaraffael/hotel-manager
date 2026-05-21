package com.example.gerenciador.hotel.infrastructure.configs.usecase;

import com.example.gerenciador.hotel.domain.port.in.quarto.BuscarQuartoPorNumeroInputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.ExistsQuartoByNumeroOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByStatusOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByTipoQuartoAndStatusListOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByTipoQuartoAndStatusOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindByTipoQuartoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindOcupadosPorTipoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.FindQuartoByNumeroOutputPort;
import com.example.gerenciador.hotel.domain.port.out.quarto.SaveQuartoOutputPort;
import com.example.gerenciador.hotel.domain.usecase.quarto.BuscarQuartoPorNumeroUseCase;
import com.example.gerenciador.hotel.domain.usecase.quarto.BuscarQuartosDisponiveisPorTipoEDataUseCase;
import com.example.gerenciador.hotel.domain.usecase.quarto.BuscarQuartosPorStatusUseCase;
import com.example.gerenciador.hotel.domain.usecase.quarto.BuscarQuartosPorTipoEStatusUseCase;
import com.example.gerenciador.hotel.domain.usecase.quarto.BuscarQuartosPorTipoUseCase;
import com.example.gerenciador.hotel.domain.usecase.quarto.CriarQuartoUseCase;
import com.example.gerenciador.hotel.domain.usecase.quarto.EditarQuartoUseCase;
import com.example.gerenciador.hotel.domain.usecase.quarto.ModificarStatusQuartoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartoUseCaseConfig {

    @Bean
    public BuscarQuartoPorNumeroUseCase buscarQuartoPorNumeroUseCase(
            FindQuartoByNumeroOutputPort findQuartoByNumeroOutputPort
    ) {
        return new BuscarQuartoPorNumeroUseCase(findQuartoByNumeroOutputPort);
    }

    @Bean
    public BuscarQuartosDisponiveisPorTipoEDataUseCase buscarQuartosDisponiveisPorTipoEDataUseCase(
            FindOcupadosPorTipoOutputPort findOcupadosPorTipoOutputPort,
            FindByTipoQuartoAndStatusListOutputPort findByTipoQuartoAndStatusListOutputPort
    ) {
        return new BuscarQuartosDisponiveisPorTipoEDataUseCase(
                findOcupadosPorTipoOutputPort,
                findByTipoQuartoAndStatusListOutputPort
        );
    }

    @Bean
    public BuscarQuartosPorStatusUseCase buscarQuartosPorStatusUseCase(
            FindByStatusOutputPort findByStatusOutputPort
    ) {
        return new BuscarQuartosPorStatusUseCase(findByStatusOutputPort);
    }

    @Bean
    public BuscarQuartosPorTipoUseCase buscarQuartosPorTipoUseCase(
            FindByTipoQuartoOutputPort findByTipoQuartoOutputPort
    ) {
        return new BuscarQuartosPorTipoUseCase(findByTipoQuartoOutputPort);
    }

    @Bean
    public BuscarQuartosPorTipoEStatusUseCase buscarQuartosPorTipoEStatusUseCase(
            FindByTipoQuartoAndStatusOutputPort findByTipoQuartoAndStatusOutputPort
    ) {
        return new BuscarQuartosPorTipoEStatusUseCase(findByTipoQuartoAndStatusOutputPort);
    }

    @Bean
    public CriarQuartoUseCase criarQuartoUseCase(
            ExistsQuartoByNumeroOutputPort existsQuartoByNumeroOutputPort,
            SaveQuartoOutputPort saveQuartoOutputPort
    ) {
        return new CriarQuartoUseCase(existsQuartoByNumeroOutputPort, saveQuartoOutputPort);
    }

    @Bean
    public EditarQuartoUseCase editarQuartoUseCase(
            BuscarQuartoPorNumeroInputPort buscarQuartoPorNumeroInputPort,
            SaveQuartoOutputPort saveQuartoOutputPort
    ) {
        return new EditarQuartoUseCase(buscarQuartoPorNumeroInputPort, saveQuartoOutputPort);
    }

    @Bean
    public ModificarStatusQuartoUseCase modificarStatusQuartoUseCase(
            BuscarQuartoPorNumeroInputPort buscarQuartoPorNumeroInputPort,
            SaveQuartoOutputPort saveQuartoOutputPort
    ) {
        return new ModificarStatusQuartoUseCase(buscarQuartoPorNumeroInputPort, saveQuartoOutputPort);
    }
}

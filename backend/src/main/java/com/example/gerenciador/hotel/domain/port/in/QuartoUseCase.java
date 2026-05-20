package com.example.gerenciador.hotel.domain.port.in;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface QuartoUseCase {

    Quarto criarQuarto(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria, StatusQuarto statusQuarto);
    Quarto buscarQuartoPorNumero(String numero);
    Quarto editarQuarto(String numero, TipoQuarto tipoQuarto, int capacidade, double precoDiaria, StatusQuarto statusQuarto);
    Quarto modificarStatusQuarto(String numero, StatusQuarto statusQuarto);
    Page<Quarto> buscarQuartosPorTipo(TipoQuarto tipoQuarto, Pageable pageable);
    Page<Quarto> buscarQuartosPorTipoEStatus(TipoQuarto tipoQuarto, StatusQuarto statusQuarto, Pageable pageable);
    Page<Quarto> buscarQuartosPorStatus(StatusQuarto statusQuarto, Pageable pageable);
    List<Quarto> buscarQuartosDisponiveisPorTipoEData(TipoQuarto tipoQuarto, Date dataEntrada, Date dataSaida);
}


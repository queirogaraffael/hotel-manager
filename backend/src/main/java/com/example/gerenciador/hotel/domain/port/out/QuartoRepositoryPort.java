package com.example.gerenciador.hotel.domain.port.out;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import com.example.gerenciador.hotel.domain.model.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface QuartoRepositoryPort {

    Quarto save(Quarto quarto);
    Optional<Quarto> findById(Long id);
    Optional<Quarto> findByNumero(String numero);
    boolean existsByNumero(String numero);
    Page<Quarto> findByTipoQuarto(TipoQuarto tipoQuarto, Pageable pageable);
    Page<Quarto> findByTipoQuartoAndStatus(TipoQuarto tipoQuarto, StatusQuarto status, Pageable pageable);
    Page<Quarto> findByStatus(StatusQuarto status, Pageable pageable);
    List<Quarto> findOcupadosPorTipo(TipoQuarto tipoQuarto, Date dataInicial, Date dataFinal, List<StatusReserva> statusReservas);
    List<Quarto> findByTipoQuartoAndStatusList(TipoQuarto tipoQuarto, StatusQuarto status);
}

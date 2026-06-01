package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.quarto;

import com.example.gerenciador.hotel.domain.enums.StatusQuarto;
import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.enums.TipoQuarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuartoJpaRepository extends JpaRepository<QuartoEntity, Long> {

    Optional<QuartoEntity> findByNumero(String numero);

    boolean existsByNumero(String numero);

    Page<QuartoEntity> findByTipoQuarto(TipoQuarto tipoQuarto, Pageable pageable);

    Page<QuartoEntity> findByTipoQuartoAndStatusQuarto(TipoQuarto tipoQuarto, StatusQuarto statusQuarto, Pageable pageable);

    Page<QuartoEntity> findByStatusQuarto(StatusQuarto statusQuarto, Pageable pageable);

    @Query("SELECT q FROM QuartoEntity q JOIN q.reservas r " +
            "WHERE q.tipoQuarto = :tipoQuarto " +
            "AND r.dataEntrada <= :dataFinal " +
            "AND r.dataSaida >= :dataInicial " +
            "AND r.statusReserva IN :statusReservas")
    List<QuartoEntity> findOcupadosPorTipo(
            @Param("tipoQuarto") TipoQuarto tipoQuarto,
            @Param("dataInicial") Date dataInicial,
            @Param("dataFinal") Date dataFinal,
            @Param("statusReservas") List<StatusReserva> statusReservas);

    List<QuartoEntity> findByTipoQuartoAndStatusQuarto(TipoQuarto tipoQuarto, StatusQuarto statusQuarto);
}

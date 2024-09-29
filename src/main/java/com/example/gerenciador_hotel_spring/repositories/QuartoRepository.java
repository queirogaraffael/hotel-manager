package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO;
import com.example.gerenciador_hotel_spring.entities.Quarto;
import com.example.gerenciador_hotel_spring.enums.StatusQuarto;
import com.example.gerenciador_hotel_spring.enums.StatusReserva;
import com.example.gerenciador_hotel_spring.enums.TipoQuarto;
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
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    Optional<Quarto> findByNumero(String numero);

    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE q.tipoQuarto = :tipoQuarto")
    Page<QuartoResponseDTO> findQuartosDTOByTipoPageados(@Param("tipoQuarto") TipoQuarto tipoQuarto, Pageable pageable);

    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE (q.tipoQuarto = :tipoQuarto AND q.statusQuarto = :statusQuarto)")
    Page<QuartoResponseDTO> buscarQuartosDTOPorTipoEPorStatusPaginadas(@Param("tipoQuarto") TipoQuarto tipoQuarto, @Param("statusQuarto") StatusQuarto statusQuarto, Pageable pageable);


    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE q.statusQuarto = :statusQuarto")
    Page<QuartoResponseDTO> buscarQuartosDTOPorStatusPaginadas(@Param("statusQuarto") StatusQuarto statusQuarto, Pageable pageable);


    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.QuartoOcupadoDTO(q.id) " +
            "FROM Reserva r JOIN r.quarto q " +
            "WHERE q.tipoQuarto = :tipoQuarto " +
            "AND r.dataEntrada <= :dataFinal " +
            "AND r.dataSaida >= :dataInicial " +
            "AND (r.statusReserva IN :statusReservas)")
    List<QuartoResponseDTO> getQuartosOcupadosPorTipo(
            @Param("tipoQuarto") TipoQuarto tipoQuarto,
            @Param("dataInicial") Date dataInicial,
            @Param("dataFinal") Date dataFinal,
            @Param("statusReservas") List<StatusReserva> statusReservas);

    @Query("SELECT new com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE (q.tipoQuarto = :tipoQuarto AND q.statusQuarto = :statusQuarto)")
    List<QuartoResponseDTO> buscarQuartosDTOPorTipoEPorStatus(@Param("tipoQuarto") TipoQuarto tipoQuarto, @Param("statusQuarto") StatusQuarto statusQuarto);

}

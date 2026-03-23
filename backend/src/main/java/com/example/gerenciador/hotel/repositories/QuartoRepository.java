package com.example.gerenciador.hotel.repositories;


import com.example.gerenciador.hotel.entities.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

        /*
    Optional<Quarto> findByNumero(String numero);

    @Query("SELECT new com.example.gerenciador.hotel.dtos.quarto.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE q.tipoQuarto = :tipoQuarto")
    Page<QuartoResponseDTO> findQuartosDTOByTipoPageados(@Param("tipoQuarto") TipoQuarto tipoQuarto, Pageable pageable);

    @Query("SELECT new com.example.gerenciador.hotel.dtos.quarto.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE (q.tipoQuarto = :tipoQuarto AND q.statusQuarto = :statusQuarto)")
    Page<QuartoResponseDTO> buscarQuartosDTOPorTipoEPorStatusPaginadas(@Param("tipoQuarto") TipoQuarto tipoQuarto, @Param("statusQuarto") StatusQuarto statusQuarto, Pageable pageable);


    @Query("SELECT new com.example.gerenciador.hotel.dtos.quarto.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE q.statusQuarto = :statusQuarto")
    Page<QuartoResponseDTO> buscarQuartosDTOPorStatusPaginadas(@Param("statusQuarto") StatusQuarto statusQuarto, Pageable pageable);


    @Query("SELECT new com.example.gerenciador.hotel.dtos.quarto.QuartoOcupadoDTO(q.id) " +
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

    @Query("SELECT new com.example.gerenciador.hotel.dtos.quarto.QuartoResponseDTO(q.id, q.numero, q.tipoQuarto) FROM Quarto q WHERE (q.tipoQuarto = :tipoQuarto AND q.statusQuarto = :statusQuarto)")
    List<QuartoResponseDTO> buscarQuartosDTOPorTipoEPorStatus(@Param("tipoQuarto") TipoQuarto tipoQuarto, @Param("statusQuarto") StatusQuarto statusQuarto);


         */
}

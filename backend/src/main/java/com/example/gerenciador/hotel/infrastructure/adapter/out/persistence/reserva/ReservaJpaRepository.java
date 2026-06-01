package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.reserva;

import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaJpaRepository extends JpaRepository<ReservaEntity, Long> {

    @Query("SELECT COUNT(r) = 0 FROM ReservaEntity r " +
            "WHERE r.quarto.numero = :numeroQuarto " +
            "AND (r.dataEntrada <= :dataSaida AND r.dataSaida >= :dataEntrada)")
    boolean quartoEstaDisponivelParaData(
            @Param("dataEntrada") LocalDateTime dataEntrada,
            @Param("dataSaida") LocalDateTime dataSaida,
            @Param("numeroQuarto") String numeroQuarto);

    @Query("SELECT r FROM ReservaEntity r JOIN r.hospede h JOIN h.user u " +
            "WHERE u.cpf = :cpf AND r.statusReserva = :status")
    Page<ReservaEntity> findByHospedeEStatus(
            @Param("cpf") String cpf,
            @Param("status") StatusReserva status,
            Pageable pageable);

    @Query("SELECT r FROM ReservaEntity r JOIN r.hospede h JOIN h.user u " +
            "WHERE u.cpf = :cpf AND r.statusReserva IN (:agendado, :emUso)")
    Page<ReservaEntity> findAgendadasEmUsoPorCpf(
            @Param("cpf") String cpf,
            @Param("agendado") StatusReserva agendado,
            @Param("emUso") StatusReserva emUso,
            Pageable pageable);

    @Query("SELECT r FROM ReservaEntity r JOIN r.hospede h JOIN h.user u " +
            "WHERE u.cpf = :cpf AND r.statusReserva IN (:finalizado, :cancelado)")
    Page<ReservaEntity> findFinalizadasCanceladasPorCpf(
            @Param("cpf") String cpf,
            @Param("finalizado") StatusReserva finalizado,
            @Param("cancelado") StatusReserva cancelado,
            Pageable pageable);
}

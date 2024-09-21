package com.example.gerenciador_hotel_spring.repositories;

import com.example.gerenciador_hotel_spring.dtos.ReservaDTO;
import com.example.gerenciador_hotel_spring.entities.Reserva;
import com.example.gerenciador_hotel_spring.enums.StatusReserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {


    @Query("SELECT NEW com.example.gerenciador_hotel_spring.dtos.ReservaDTO(r.id, r.dataEntrada, r.dataSaida, r.statusReserva) " +
            "FROM Reserva r LEFT JOIN r.hospede h " +
            "WHERE h.cpf = :cpf AND (r.statusReserva = :statusAgendado OR r.statusReserva = :statusEmUso)")
    Page<ReservaDTO> findReservasDTOAgendadasEmUsoPorCPF(@Param("cpf") String cpf,
                                                 @Param("statusAgendado") StatusReserva statusAgendado,
                                                 @Param("statusEmUso") StatusReserva statusEmUso,
                                                 Pageable pageable);


    @Query("SELECT NEW com.example.gerenciador_hotel_spring.dtos.ReservaDTO(r.id, r.dataEntrada, r.dataSaida, r.statusReserva) " +
            "FROM Reserva r LEFT JOIN r.hospede h " +
            "WHERE h.cpf = :cpf AND (r.statusReserva = :statusFinalizado OR r.statusReserva = :statusCancelado)")
    Page<ReservaDTO> findReservasDTOHospedeFinalizadasCanceladasByCPF(@Param("cpf") String cpf,
                                                 @Param("statusFinalizado") StatusReserva statusFinalizado,
                                                 @Param("statusCancelado") StatusReserva statusCancelado,
                                                 Pageable pageable);

}

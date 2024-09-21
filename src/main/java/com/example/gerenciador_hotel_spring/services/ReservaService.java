package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.dtos.ReservaDTO;
import com.example.gerenciador_hotel_spring.entities.Reserva;
import com.example.gerenciador_hotel_spring.enums.StatusReserva;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;


    @Transactional
    public Reserva criarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }


    @Transactional(readOnly = true)
    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada para esse id " + id));
    }


    @Transactional
    public Reserva modificaStatusReservaById(Long id, StatusReserva statusReserva) {
        Reserva reserva = getReservaById(id);

        reserva.setStatusReserva(statusReserva);

        return reservaRepository.save(reserva);
    }


    @Transactional(readOnly = true)
    public Page<ReservaDTO> buscarReservasDTOAgendadasEmUsoPorCPF(String cpf, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reservaRepository.findReservasDTOAgendadasEmUsoPorCPF(cpf, StatusReserva.AGENDADO, StatusReserva.EM_USO, pageable);
    }


    @Transactional(readOnly = true)
    public Page<ReservaDTO> buscaReservasDTOHospedeFinalizadasCanceladasByCPF(String cpf, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reservaRepository.findReservasDTOHospedeFinalizadasCanceladasByCPF(cpf, StatusReserva.FINALIZADO, StatusReserva.CANCELADO, pageable);
    }


}

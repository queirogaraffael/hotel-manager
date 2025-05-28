package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.domain.entities.Hospede;
import com.example.gerenciador.hotel.domain.entities.Quarto;
import com.example.gerenciador.hotel.domain.entities.Reserva;
import com.example.gerenciador.hotel.domain.enums.StatusReserva;
import com.example.gerenciador.hotel.domain.repositories.HospedeRepository;
import com.example.gerenciador.hotel.domain.repositories.QuartoRepository;
import com.example.gerenciador.hotel.domain.repositories.ReservaRepository;
import com.example.gerenciador.hotel.shared.dtos.reserva.ReservaResponseDTO;
import com.example.gerenciador.hotel.shared.exceptions.QuartoNaoEstaDisponivelParaDataException;
import com.example.gerenciador.hotel.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    @Transactional
    public Reserva criarReserva(Reserva reserva, String cpf, String numeroQuarto) {

        Optional<Quarto> quarto = quartoRepository.findByNumero(numeroQuarto);

        if(!quarto.isPresent()){
            throw new ResourceNotFoundException("Quarto inválido. Cadastre o quarto com o número " + numeroQuarto + " primeiro.");
        }

        boolean quartoEstaDisponivelParaData = reservaRepository.quartoEstaDisponivelParaDataDeReserva(reserva.getDataEntrada(), reserva.getDataSaida(), numeroQuarto);

        if (!quartoEstaDisponivelParaData) {
            throw new QuartoNaoEstaDisponivelParaDataException("Quarto não está disponivel para reserva para esta data.");
        }

        Hospede hospede = hospedeRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Hóspede não encontrado."));

        reserva.setHospede(hospede);
        reserva.setQuarto(quarto.get());
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
    public Page<ReservaResponseDTO> buscarReservasDTOPorHospedeEStatusPaginadas(String cpfHospede, StatusReserva status, Pageable pageable) {
        return reservaRepository.buscarReservasDTOPorHospedeEStatusPaginadas(cpfHospede, status, pageable);
    }


    @Transactional(readOnly = true)
    public Page<ReservaResponseDTO> buscarReservasDTOAgendadasEmUsoPorCPF(String cpf, Pageable pageable) {
        return reservaRepository.findReservasDTOAgendadasEmUsoPorCPF(cpf, StatusReserva.AGENDADO, StatusReserva.EM_USO, pageable);
    }


    @Transactional(readOnly = true)
    public Page<ReservaResponseDTO> buscaReservasDTOHospedeFinalizadasCanceladasByCPF(String cpf, Pageable pageable) {
        return reservaRepository.findReservasDTOHospedeFinalizadasCanceladasByCPF(cpf, StatusReserva.FINALIZADO, StatusReserva.CANCELADO, pageable);
    }


}

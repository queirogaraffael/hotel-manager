package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.dtos.QuartoResponseDTO;
import com.example.gerenciador_hotel_spring.entities.Quarto;
import com.example.gerenciador_hotel_spring.enums.StatusQuarto;
import com.example.gerenciador_hotel_spring.enums.StatusReserva;
import com.example.gerenciador_hotel_spring.enums.TipoQuarto;
import com.example.gerenciador_hotel_spring.exceptions.QuartoJaExisteException;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {


    @Autowired
    QuartoRepository quartoRepository;


    @Transactional
    public Quarto criarQuarto(Quarto quarto) {
        Optional<Quarto> quartoOptional = quartoRepository.findByNumero(quarto.getNumero());

        if (quartoOptional.isPresent()) {
            throw new QuartoJaExisteException("Quarto já cadastrado com esse número");
        }

        return quartoRepository.save(quarto);

    }


    @Transactional(readOnly = true)
    public Quarto getQuartoByNumero(String numero) {
        return quartoRepository.findByNumero(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto com número " + numero + " não encontrado."));
    }


    @Transactional(readOnly = true)
    public Page<QuartoResponseDTO> buscarQuartosDTOPorTipoPaginadas(TipoQuarto tipoQuarto, Pageable pageable) {
        return quartoRepository.findQuartosDTOByTipoPageados(tipoQuarto,pageable);
    }


    @Transactional(readOnly = true)
    public Page<QuartoResponseDTO> buscaQuartoPorTipoDisponiveisPorData(TipoQuarto tipoQuarto, Date dataEntrada, Date dataSaida, Pageable pageable){

        StatusReserva statusAgendado = StatusReserva.AGENDADO;
        StatusReserva statusEmUso = StatusReserva.EM_USO;
        StatusReserva statusManutencao = StatusReserva.MANUTENCAO;

        List<StatusReserva> statusReservas = Arrays.asList(statusAgendado, statusEmUso, statusManutencao);

        List<QuartoResponseDTO> quartosOcupados = quartoRepository.getQuartosOcupadosPorTipo(tipoQuarto, dataEntrada, dataSaida, statusReservas);

        List<QuartoResponseDTO> quartosDisponiveis = quartoRepository.buscarQuartosDTOPorTipoEPorStatus(tipoQuarto, StatusQuarto.DISPONIVEL);

        List<QuartoResponseDTO> quartosPorTipoDisponiveisPorData = quartosDisponiveis.stream()
                .filter(quarto -> !quartosOcupados.contains(quarto))
                .toList();

        return new PageImpl<>(quartosPorTipoDisponiveisPorData, pageable, quartosPorTipoDisponiveisPorData.size());
    }


    @Transactional(readOnly = true)
    public Page<QuartoResponseDTO> buscarQuartosDTOPorTipoEPorStatusPaginadas(TipoQuarto tipoQuarto, StatusQuarto statusQuarto, Pageable pageable) {
        return quartoRepository.buscarQuartosDTOPorTipoEPorStatusPaginadas(tipoQuarto, statusQuarto, pageable);
    }


    @Transactional(readOnly = true)
    public Page<QuartoResponseDTO> buscarQuartosDTOPorStatusPaginadas(StatusQuarto statusQuarto, Pageable pageable) {
        return quartoRepository.buscarQuartosDTOPorStatusPaginadas(statusQuarto, pageable);
    }


    @Transactional
    public Quarto modificaStatusQuartoByNumero(String numero, StatusQuarto statusQuarto) {
        Quarto quarto = getQuartoByNumero(numero);

       quarto.setStatusQuarto(statusQuarto);

        return quartoRepository.save(quarto);
    }


    @Transactional
    public Quarto editaQuartoByNumero(String numero, Quarto quartoModificado) {

        Quarto quarto = getQuartoByNumero(numero);

        boolean quartoComNumeroJaExiste = quartoRepository.existsByNumero(quartoModificado.getNumero());

        if(quartoComNumeroJaExiste){
            throw new QuartoJaExisteException("Quarto com esse número já existe. Tente com outro número.");
        }

        quarto.setNumero(quartoModificado.getNumero());
        quarto.setTipoQuarto(quartoModificado.getTipoQuarto());
        quarto.setCapacidade(quartoModificado.getCapacidade());
        quarto.setPrecoDiaria(quartoModificado.getPrecoDiaria());
        quarto.setStatusQuarto(quartoModificado.getStatusQuarto());

        return quartoRepository.save(quarto);

    }

}

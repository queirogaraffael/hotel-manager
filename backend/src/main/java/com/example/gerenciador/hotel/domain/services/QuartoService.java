package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.repositories.QuartoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartoService {


    @Autowired
    private QuartoRepository quartoRepository;

    /*
    @Transactional
    public QuartoRequestDTO criarQuarto(QuartoRequestDTO quartoDTO) {
        Optional<Quarto> quartoOptional = quartoRepository.findByNumero(quartoDTO.numero());

        if (quartoOptional.isPresent()) {
            throw new QuartoJaExisteException("Quarto já cadastrado com esse número");
        }


        Quarto quarto = new Quarto();

        quarto.setNumero(quartoDTO.numero());
        quarto.setTipoQuarto(quartoDTO.tipoQuarto());
        quarto.setCapacidade(quartoDTO.capacidade());
        quarto.setPrecoDiaria(quartoDTO.precoDiaria());
        quarto.setStatusQuarto(quartoDTO.statusQuarto());

        quartoRepository.save(quarto);

        return quartoDTO;

    }


    @Transactional(readOnly = true)
    public Quarto getQuartoByNumero(String numero) {
        return quartoRepository.findByNumero(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto com número " + numero + " não encontrado."));
    }


    @Transactional(readOnly = true)
    public Page<QuartoResponseDTO> buscarQuartosDTOPorTipoPaginadas(TipoQuarto tipoQuarto, Pageable pageable) {
        return quartoRepository.findQuartosDTOByTipoPageados(tipoQuarto, pageable);
    }


    @Transactional(readOnly = true)
    public Page<QuartoResponseDTO> buscaQuartoPorTipoDisponiveisPorData(TipoQuarto tipoQuarto, Date dataEntrada, Date dataSaida, Pageable pageable) {

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
    public QuartoUpdateDTO editaQuartoByNumero(String numero, QuartoUpdateDTO quartoModificado) {
        Quarto quarto = getQuartoByNumero(numero);

        quarto.setTipoQuarto(quartoModificado.tipoQuarto());
        quarto.setCapacidade(quartoModificado.capacidade());
        quarto.setPrecoDiaria(quartoModificado.precoDiaria());
        quarto.setStatusQuarto(quartoModificado.statusQuarto());

        quartoRepository.save(quarto);

        return quartoModificado;

    }
     */

}

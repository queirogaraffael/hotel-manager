package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.entities.Quarto;
import com.example.gerenciador_hotel_spring.exceptions.QuartoJaExisteException;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

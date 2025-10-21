package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    /*
    @Transactional
    public Endereco modificaEnderecoById(Long id, Endereco enderecoModificado) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereco com este id " + id + "não encontrado para modificação."));

        endereco.setRua(enderecoModificado.getRua());
        endereco.setNumero(enderecoModificado.getNumero());
        endereco.setCidade(enderecoModificado.getCidade());
        endereco.setBairro(enderecoModificado.getBairro());
        endereco.setEstado(enderecoModificado.getEstado());

        return enderecoRepository.save(endereco);

    }

     */

}

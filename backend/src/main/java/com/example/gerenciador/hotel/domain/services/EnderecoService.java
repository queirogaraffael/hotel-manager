package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.EnderecoUseCase;
import com.example.gerenciador.hotel.domain.port.out.EnderecoRepositoryPort;
import com.example.gerenciador.hotel.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService implements EnderecoUseCase {

    private final EnderecoRepositoryPort enderecoRepository;

    @Override
    @Transactional
    public Endereco modificarEndereco(Long id, Endereco enderecoModificado) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço com id " + id + " não encontrado."));

        endereco.setRua(enderecoModificado.getRua());
        endereco.setNumero(enderecoModificado.getNumero());
        endereco.setCidade(enderecoModificado.getCidade());
        endereco.setBairro(enderecoModificado.getBairro());
        endereco.setEstado(enderecoModificado.getEstado());
        endereco.setCep(enderecoModificado.getCep());

        return enderecoRepository.save(endereco);
    }
}


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

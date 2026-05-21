package com.example.gerenciador.hotel.domain.usecase;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.endereco.ModificarEnderecoUseCase;
import com.example.gerenciador.hotel.domain.port.out.EnderecoRepositoryPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService implements ModificarEnderecoUseCase {

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



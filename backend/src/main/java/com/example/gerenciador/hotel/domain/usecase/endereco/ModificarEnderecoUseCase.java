package com.example.gerenciador.hotel.domain.usecase.endereco;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.endereco.ModificarEnderecoInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.FindEnderecoByIdOutputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModificarEnderecoUseCase implements ModificarEnderecoInputPort {

    private final FindEnderecoByIdOutputPort findEnderecoByIdOutputPort;
    private final SaveEnderecoOutputPort saveEnderecoOutputPort;

    @Override
    @Transactional
    public Endereco modificarEndereco(Long id, Endereco enderecoModificado) {
        Endereco endereco = findEnderecoByIdOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço com id " + id + " não encontrado."));

        endereco.setRua(enderecoModificado.getRua());
        endereco.setNumero(enderecoModificado.getNumero());
        endereco.setCidade(enderecoModificado.getCidade());
        endereco.setBairro(enderecoModificado.getBairro());
        endereco.setEstado(enderecoModificado.getEstado());
        endereco.setCep(enderecoModificado.getCep());

        return saveEnderecoOutputPort.save(endereco);
    }
}

package com.example.gerenciador.hotel.domain.usecase.endereco;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.port.in.endereco.ModificarEnderecoInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.FindEnderecoByIdOutputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.exception.EnderecoNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class ModificarEnderecoUseCase implements ModificarEnderecoInputPort {

    private final FindEnderecoByIdOutputPort findEnderecoByIdOutputPort;
    private final SaveEnderecoOutputPort saveEnderecoOutputPort;

    public ModificarEnderecoUseCase(FindEnderecoByIdOutputPort findEnderecoByIdOutputPort, SaveEnderecoOutputPort saveEnderecoOutputPort) {
        this.findEnderecoByIdOutputPort = findEnderecoByIdOutputPort;
        this.saveEnderecoOutputPort = saveEnderecoOutputPort;
    }

    @Override
    @Transactional
    public Endereco modificarEndereco(Long id, Endereco enderecoModificado) {
        Endereco endereco = findEnderecoByIdOutputPort.findById(id)
                .orElseThrow(() -> new EnderecoNotFoundException("Endereço com id " + id + " não encontrado."));

        endereco.setRua(enderecoModificado.getRua());
        endereco.setNumero(enderecoModificado.getNumero());
        endereco.setCidade(enderecoModificado.getCidade());
        endereco.setBairro(enderecoModificado.getBairro());
        endereco.setEstado(enderecoModificado.getEstado());
        endereco.setCep(enderecoModificado.getCep());

        return saveEnderecoOutputPort.save(endereco);
    }
}

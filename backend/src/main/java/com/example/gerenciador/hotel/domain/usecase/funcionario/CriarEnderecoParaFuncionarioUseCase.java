package com.example.gerenciador.hotel.domain.usecase.funcionario;

import com.example.gerenciador.hotel.domain.model.Endereco;
import com.example.gerenciador.hotel.domain.model.Funcionario;
import com.example.gerenciador.hotel.domain.port.in.funcionario.BuscarFuncionarioPorCpfInputPort;
import com.example.gerenciador.hotel.domain.port.out.endereco.SaveEnderecoOutputPort;
import com.example.gerenciador.hotel.domain.port.out.funcionario.SaveFuncionarioOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarEnderecoParaFuncionarioUseCase implements com.example.gerenciador.hotel.domain.port.in.funcionario.CriarEnderecoParaFuncionarioUseCase {

    private final BuscarFuncionarioPorCpfInputPort buscarFuncionarioPorCpfInputPort;
    private final SaveEnderecoOutputPort saveEnderecoOutputPort;
    private final SaveFuncionarioOutputPort saveFuncionarioOutputPort;

    @Override
    @Transactional
    public Endereco criarEnderecoParaFuncionario(String cpf, Endereco endereco) {
        Funcionario funcionario = buscarFuncionarioPorCpfInputPort.buscarFuncionarioPorCpf(cpf);
        Endereco enderecoSalvo = saveEnderecoOutputPort.save(endereco);
        funcionario.getUser().setEndereco(enderecoSalvo);
        saveFuncionarioOutputPort.save(funcionario);
        return enderecoSalvo;
    }
}

package com.example.gerenciador.hotel.domain.services;

import com.example.gerenciador.hotel.domain.entities.ExtratoFuncionario;
import com.example.gerenciador.hotel.domain.entities.Funcionario;
import com.example.gerenciador.hotel.domain.repositories.ExtratoFuncionarioRepository;
import com.example.gerenciador.hotel.domain.repositories.FuncionarioRepository;
import com.example.gerenciador.hotel.shared.dtos.extratofuncionario.ExtratoFuncionarioDTO;
import com.example.gerenciador.hotel.shared.exceptions.ExtratoJaExisteParaMesReferenteException;
import com.example.gerenciador.hotel.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExtratoFuncionarioService {

    @Autowired
    private ExtratoFuncionarioRepository extratoFuncionarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @Transactional
    public ExtratoFuncionarioDTO criaExtratoFuncionario(String cpf, ExtratoFuncionarioDTO extratoFuncionarioDTO) {

        if (extratoFuncionarioRepository.existeExtratoDeFuncionarioParaMes(cpf, extratoFuncionarioDTO.dataExtrato())) {
            throw new ExtratoJaExisteParaMesReferenteException("Extrato já existe para o mês referente.");
        }

        ExtratoFuncionario extrato = new ExtratoFuncionario();

        extrato.setDataExtrato(extratoFuncionarioDTO.dataExtrato());
        extrato.setHorasTrabalhadas(extratoFuncionarioDTO.horasTrabalhadas());
        extrato.setValorHora(extratoFuncionarioDTO.valorHora());
        extrato.setSalario(extratoFuncionarioDTO.salario());

        Funcionario funcionario = funcionarioRepository.findFuncionarioComExtratoByCPF(cpf).orElseThrow(() -> new ResourceNotFoundException("Funcionário com o CPF " + cpf + " não encontrado."));

        extrato.setFuncionario(funcionario);
        funcionario.getExtratoFuncionario().add(extrato);

        extratoFuncionarioRepository.save(extrato);

        return extratoFuncionarioDTO;

    }


    @Transactional(readOnly = true)
    public ExtratoFuncionario getExtratoFuncionarioUnicoById(Long idExtrato) {
        return extratoFuncionarioRepository.findById(idExtrato).orElseThrow(() -> new ResourceNotFoundException("Extrato com id " + idExtrato + " não encontrado."));
    }


    @Transactional(readOnly = true)
    public Page<ExtratoFuncionarioResponseDTO> getExtratosFuncionarioDTOPorCPFPaginados(String cpf, Pageable pageable) {
        return extratoFuncionarioRepository.findExtratosFuncionarioDTOByCPF(cpf, pageable);
    }


    @Transactional
    public ExtratoFuncionarioDTO editaExtratoFuncionarioById(Long id, ExtratoFuncionarioDTO extratoFuncionarioModificado) {

        ExtratoFuncionario extratoFuncionario = getExtratoFuncionarioUnicoById(id);

        extratoFuncionario.setDataExtrato(extratoFuncionarioModificado.dataExtrato());
        extratoFuncionario.setHorasTrabalhadas(extratoFuncionarioModificado.horasTrabalhadas());
        extratoFuncionario.setValorHora(extratoFuncionarioModificado.valorHora());
        extratoFuncionario.setSalario(extratoFuncionarioModificado.salario());

        extratoFuncionarioRepository.save(extratoFuncionario);

        return extratoFuncionarioModificado;

    }


    @Transactional
    public void deletaExtratoFuncionarioById(Long id) {
        extratoFuncionarioRepository.deleteById(id);
    }


}

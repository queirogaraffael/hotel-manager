package com.example.gerenciador_hotel_spring.services;

import com.example.gerenciador_hotel_spring.dtos.ExtratoFuncionarioResponseDTO;
import com.example.gerenciador_hotel_spring.entities.ExtratoFuncionario;
import com.example.gerenciador_hotel_spring.entities.Funcionario;
import com.example.gerenciador_hotel_spring.exceptions.ExtratoJaExisteParaMesReferenteException;
import com.example.gerenciador_hotel_spring.exceptions.ResourceNotFoundException;
import com.example.gerenciador_hotel_spring.repositories.ExtratoFuncionarioRepository;
import com.example.gerenciador_hotel_spring.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExtratoFuncionarioService {

    @Autowired
    ExtratoFuncionarioRepository extratoFuncionarioRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;


    @Transactional
    public ExtratoFuncionario criaExtratoFuncionario(String cpf, ExtratoFuncionario extrato) {

        if(extratoFuncionarioRepository.existeExtratoDeFuncionarioParaMes(cpf, extrato.getMesReferente())){
            throw new ExtratoJaExisteParaMesReferenteException("Extrato já existe para o mês referente.");
        }

        Funcionario funcionario = funcionarioRepository.findFuncionarioComExtratoByCPF(cpf).orElseThrow(() -> new ResourceNotFoundException("Funcionário com o CPF " + cpf + " não encontrado."));

        extrato.setFuncionario(funcionario);
        funcionario.getExtratoFuncionario().add(extrato);

        return extratoFuncionarioRepository.save(extrato);

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
    public ExtratoFuncionario editaExtratoFuncionarioById(Long id, ExtratoFuncionario extratoFuncionarioModificado) {

        ExtratoFuncionario extratoFuncionario = getExtratoFuncionarioUnicoById(id);

        extratoFuncionario.setMesReferente(extratoFuncionarioModificado.getMesReferente());
        extratoFuncionario.setHorasTrabalhadas(extratoFuncionarioModificado.getHorasTrabalhadas());
        extratoFuncionario.setValorHora(extratoFuncionarioModificado.getValorHora());
        extratoFuncionario.setSalario(extratoFuncionarioModificado.getSalario());

        return extratoFuncionarioRepository.save(extratoFuncionario);

    }


    @Transactional
    public void deletaExtratoFuncionarioById(Long id) {
        extratoFuncionarioRepository.deleteById(id);
    }


}

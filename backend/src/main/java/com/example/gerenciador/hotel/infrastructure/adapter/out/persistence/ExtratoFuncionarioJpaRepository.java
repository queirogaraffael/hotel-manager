package com.example.gerenciador.hotel.infrastructure.adapter.out.persistence;

import com.example.gerenciador.hotel.infrastructure.adapter.out.persistence.entity.ExtratoFuncionarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExtratoFuncionarioJpaRepository extends JpaRepository<ExtratoFuncionarioEntity, Long> {

    @Query("SELECT e FROM ExtratoFuncionarioEntity e JOIN e.funcionario f JOIN f.user u WHERE u.cpf = :cpf")
    Page<ExtratoFuncionarioEntity> findByCpfFuncionario(@Param("cpf") String cpf, Pageable pageable);

    @Query("SELECT COUNT(e) > 0 FROM ExtratoFuncionarioEntity e JOIN e.funcionario f JOIN f.user u " +
            "WHERE u.cpf = :funcionarioCpf " +
            "AND EXTRACT(YEAR FROM e.dataExtrato) = EXTRACT(YEAR FROM :data) " +
            "AND EXTRACT(MONTH FROM e.dataExtrato) = EXTRACT(MONTH FROM :data)")
    boolean existeExtratoDeFuncionarioParaMes(
            @Param("funcionarioCpf") String funcionarioCpf,
            @Param("data") Date dataExtrato);
}

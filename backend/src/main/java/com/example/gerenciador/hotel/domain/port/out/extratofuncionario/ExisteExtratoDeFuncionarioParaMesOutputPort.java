package com.example.gerenciador.hotel.domain.port.out.extratofuncionario;

import java.util.Date;

public interface ExisteExtratoDeFuncionarioParaMesOutputPort {
    boolean existeExtratoDeFuncionarioParaMes(String funcionarioCpf, Date dataExtrato);
}

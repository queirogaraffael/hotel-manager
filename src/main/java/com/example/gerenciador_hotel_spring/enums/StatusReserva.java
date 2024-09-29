package com.example.gerenciador_hotel_spring.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatusReserva {

    AGENDADO(0, "AGENDADO"),
    CANCELADO(1, "CANCELADO"),
    EM_USO(2, "EM_USO"),
    FINALIZADO(3, "FINALIZADO"),
    MANUTENCAO(3, "MANUTENCAO");


    private final int codigo;
    private final String descricao;

    StatusReserva(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static StatusReserva fromCodigo(int codigo) {
        for (StatusReserva statusReserva : StatusReserva.values()) {
            if (statusReserva.getCodigo() == codigo) {
                return statusReserva;
            }
        }
        throw new IllegalArgumentException("Código de prioridade inválido: " + codigo);
    }

}
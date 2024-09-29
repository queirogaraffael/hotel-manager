package com.example.gerenciador_hotel_spring.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatusQuarto {

    DISPONIVEL(0,"DISPONIVEL"),
    OCUPADO(1,"OCUPADO"),
    MANUTENCAO(2,"MANUTENCAO");

    private final int codigo;
    private final String descricao;

    StatusQuarto(int codigo, String descricao) {
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

    public static StatusQuarto fromCodigo(int codigo) {
        for (StatusQuarto statusQuarto : StatusQuarto.values()) {
            if (statusQuarto.getCodigo() == codigo) {
                return statusQuarto;
            }
        }
        throw new IllegalArgumentException("Código de prioridade inválido: " + codigo);
    }

}
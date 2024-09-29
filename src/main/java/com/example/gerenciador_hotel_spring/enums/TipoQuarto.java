package com.example.gerenciador_hotel_spring.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoQuarto {

    SOLTEIRO(0, "SOLTEIRO"),
    CASAL(1, "CASAL"),
    SUITE(2, "SUITE");

    private final int codigo;
    private final String descricao;

    TipoQuarto(int codigo, String descricao) {
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

    public static TipoQuarto fromCodigo(int codigo) {
        for (TipoQuarto tipoQuarto : TipoQuarto.values()) {
            if (tipoQuarto.getCodigo() == codigo) {
                return tipoQuarto;
            }
        }
        throw new IllegalArgumentException("Código de prioridade inválido: " + codigo);
    }
}
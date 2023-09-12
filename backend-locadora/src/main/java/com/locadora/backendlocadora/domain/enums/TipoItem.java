package com.locadora.backendlocadora.domain.enums;

import lombok.Getter;

public enum TipoItem {
    DVD("Dvd"),
    BLU_RAY("BluRay"),
    FITA("Fita");

    @Getter
    private String valor;

    private TipoItem(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return this.valor;
    }

}

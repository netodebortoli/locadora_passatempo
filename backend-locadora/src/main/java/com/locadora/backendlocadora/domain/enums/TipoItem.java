package com.locadora.backendlocadora.domain.enums;

import lombok.Getter;

public enum TipoItem {
    DVD("Dvd"),
    BLU_RAY("Blu-ray"),
    FITA("Fita");

    @Getter
    private String valor;

    private TipoItem(String valor) {
        this.valor = valor;
    }
    
    public static String[] getTiposItem() {
        return new String[] { DVD.valor, BLU_RAY.valor, FITA.valor };
    }
}

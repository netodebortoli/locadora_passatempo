package com.locadora.backendlocadora.domain.enums;

import lombok.Getter;

public enum TipoStatus {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    @Getter
    private String valor;

    TipoStatus(String valor) {
        this.valor = valor;
    }
}

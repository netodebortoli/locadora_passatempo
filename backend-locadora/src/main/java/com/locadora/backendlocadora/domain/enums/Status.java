package com.locadora.backendlocadora.domain.enums;

import lombok.Getter;

public enum Status {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    @Getter
    private String status;

    Status(String status) {
        this.status = status;
    }
}

package com.locadora.backendlocadora.entity.enums;

import lombok.Getter;

public enum Categoria {

    ACAO("Ação"),
    AVENTURA("Aventura"),
    ROMANCE("Romance"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    DOCUMENTARIO("Documentário"),
    SUSPENSE("Suspense"),
    TERROR("Terror"),
    OUTROS("Outros");

    @Getter
    private String valor;

    private Categoria(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return this.valor;
    }
}

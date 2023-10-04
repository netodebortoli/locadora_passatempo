package com.locadora.backendlocadora.domain.enums;

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

    public Categoria[] getCategorias() {
        return new Categoria[] { ACAO, AVENTURA, ROMANCE, COMEDIA, DRAMA, FICCAO_CIENTIFICA, DOCUMENTARIO, SUSPENSE,
                TERROR, OUTROS };
    }
}

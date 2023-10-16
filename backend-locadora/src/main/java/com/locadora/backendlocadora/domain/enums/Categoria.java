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

    public static String[] getCategorias() {
        return new String[] {
                ACAO.valor, AVENTURA.valor, ROMANCE.valor, COMEDIA.valor, DRAMA.valor,
                FICCAO_CIENTIFICA.valor, DOCUMENTARIO.valor, SUSPENSE.valor,
                TERROR.valor, OUTROS.valor };
    }
}

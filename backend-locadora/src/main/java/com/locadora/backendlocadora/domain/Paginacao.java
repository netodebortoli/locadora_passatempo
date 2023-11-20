package com.locadora.backendlocadora.domain;

import java.util.List;

public record Paginacao<M>(
        List<M> resultado, long totalItens, long totalPaginas) {
}

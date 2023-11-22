package com.locadora.backendlocadora.service.exception;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RegistroNaoEncontradoException extends RuntimeException {

    public <K> RegistroNaoEncontradoException(String nomeEntidade, @Positive @NotNull K id) {
        super(nomeEntidade + " de id: " + id + " não existe.");
    }

}

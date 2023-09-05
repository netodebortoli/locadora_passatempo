package com.locadora.backendlocadora.service.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String nomeEntidade, Long id) {
        super(nomeEntidade + " de ID " + id + " não encontrado.");
    }

}

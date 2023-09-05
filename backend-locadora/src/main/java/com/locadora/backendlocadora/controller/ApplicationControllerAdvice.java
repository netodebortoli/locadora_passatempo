package com.locadora.backendlocadora.controller;

import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RegistroNaoEncontradoException ex) {
        return ex.getMessage();
    }

}

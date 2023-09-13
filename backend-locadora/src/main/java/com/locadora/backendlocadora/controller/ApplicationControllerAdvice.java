package com.locadora.backendlocadora.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<Object> handleNotFoundException(RegistroNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setCodigo(errorResponse.getStatus().value());
        errorResponse.setMensagem(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError("Objeto não encontrado!");
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setCodigo(errorResponse.getStatus().value());
        errorResponse.setMensagem(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError("Ocorreu um erro de negócio!");
        return buildResponseEntity(errorResponse);

    }

}

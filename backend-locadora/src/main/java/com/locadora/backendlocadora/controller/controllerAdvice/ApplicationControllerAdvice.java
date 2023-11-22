package com.locadora.backendlocadora.controller.controllerAdvice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.ConstraintViolationException;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setCodigo(errorResponse.getStatus().value());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError("Argumento inválido!");
        errorResponse.setMensagem((ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage() + " ")
                .reduce(" ", (acc, error) -> acc + error + "\n")));
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setCodigo(errorResponse.getStatus().value());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError("Argumento inválido!");
        errorResponse.setMensagem(ex.getConstraintViolations().stream()
                .map(error -> error.getPropertyPath() + " " + error.getMessage())
                .reduce(" ", (acc, error) -> acc + error + "\n"));
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setCodigo(errorResponse.getStatus().value());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError("Tipo do argumento inválido!");
        if (ex != null && ex.getRequiredType() != null) {
            String type = ex.getRequiredType().getName();
            String[] typeParts = type.split("\\.");
            String typeName = typeParts[typeParts.length - 1];
            errorResponse.setMensagem(ex.getName() + " deve ser do tipo " + typeName);
        }
        return buildResponseEntity(errorResponse);
    }

}

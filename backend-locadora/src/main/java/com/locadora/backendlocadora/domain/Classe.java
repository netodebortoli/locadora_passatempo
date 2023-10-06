package com.locadora.backendlocadora.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Classe(
    @JsonProperty("_id") Long id,
    @NotBlank(message = "O campo Nome é obrigatório.") String nome,
    @NotNull(message = "O campo Valor é obrigatório.") Double valor,
    @NotNull(message = "O campo Prazo de Devolução é obrigatório.") Integer prazoDevolucao) {
}

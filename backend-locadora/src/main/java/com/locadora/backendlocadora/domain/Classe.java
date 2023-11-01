package com.locadora.backendlocadora.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Classe(
    @JsonProperty("_id") Long id,
    @NotBlank(message = "O campo Nome é obrigatório.") @Schema(type = "string", example = "Premium Plus", description = "Nome da classe") String nome,
    @NotNull(message = "O campo Valor é obrigatório.") @Schema(type = "number", format = "double", example = "12.00", description = "Valor da multa da classe.") Double valor,
    @NotNull(message = "O campo Prazo de Devolução é obrigatório.") @Schema(type = "number", format="integer", example = "2", description = "Prazo para devolução da classe.") Integer prazoDevolucao) {
}

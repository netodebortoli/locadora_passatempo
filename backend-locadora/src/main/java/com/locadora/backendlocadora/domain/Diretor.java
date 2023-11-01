package com.locadora.backendlocadora.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record Diretor(
        @JsonProperty("_id") @Schema(type = "long", example = "1") Long id,
        @NotBlank(message = "O campo Nome é obrigatório.") @Schema(type = "string", example = "Steven Spielberg", description = "Nome do diretor") String nome) {
}

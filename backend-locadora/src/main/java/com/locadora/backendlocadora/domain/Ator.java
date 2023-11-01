package com.locadora.backendlocadora.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonView;
import com.locadora.backendlocadora.utils.View;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record Ator(
                @JsonProperty("_id") @Schema(example = "2") Long id,
                @NotBlank(message = "O campo Nome é obrigatório.")
                @Schema(type = "string", example = "Leonardo DiCaprio", description = "Nome do ator") String nome) {
}

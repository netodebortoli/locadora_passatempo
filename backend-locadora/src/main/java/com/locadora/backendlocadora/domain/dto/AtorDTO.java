package com.locadora.backendlocadora.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record AtorDTO(
        @JsonProperty("_id") Long id,
        @NotBlank(message = "O campo Nome é obrigatório.") String nome) {
}

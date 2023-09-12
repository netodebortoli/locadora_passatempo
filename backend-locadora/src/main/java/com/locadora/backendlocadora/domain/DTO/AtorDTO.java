package com.locadora.backendlocadora.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record AtorDTO(
    @JsonProperty("_id") Long id,
    @NotBlank String nome) { 
}

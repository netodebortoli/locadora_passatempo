package com.locadora.backendlocadora.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record AtorDTO(
    @JsonProperty("_id") Long id,
    @NotBlank String nome) { 
}

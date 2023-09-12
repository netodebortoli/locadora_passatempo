package com.locadora.backendlocadora.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClasseDTO(
    @JsonProperty("_id") Long id,
    @NotBlank String nome,
    @NotNull Double valor,
    @NotNull Integer prazoDevolucao) {
}

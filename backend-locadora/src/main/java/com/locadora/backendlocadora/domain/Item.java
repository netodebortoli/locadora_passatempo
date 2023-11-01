package com.locadora.backendlocadora.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Item(
        @JsonProperty("_id") @Schema(type = "long", example = "1") Long id,
        @NotBlank(message = "O campo Número de Série é obrigatório.") @Schema(type = "string", example = "AA7700A", description = "Número de série do item") String numSerie,
        @NotBlank(message = "O campo Tipo de Item é obrigatório.") @Schema(type = "string", example = "Dvd", description = "Tipo do item") String tipoItem,
        @NotNull(message = "A Data de Aquisição é obrigatório.") @Schema(type = "string", example = "2023-10-25", description = "Data de aquisição do item") Date dataAquisicao,
        @NotNull(message = "O campo Título é obrigatório.") @Schema(type = "json", description = "Título associado ao item") Titulo titulo) {
}
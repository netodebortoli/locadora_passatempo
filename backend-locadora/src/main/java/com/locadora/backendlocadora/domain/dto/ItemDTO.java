package com.locadora.backendlocadora.domain.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDTO(
                @JsonProperty("_id") Long id,
                @NotBlank(message = "O campo Número de Série é obrigatório.") String numSerie,
                @NotBlank(message = "O campo Tipo de Item é obrigatório.") String tipoItem,
                @NotNull(message = "A Data de Aquisição é obrigatório.") 
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Date dataAquisicao) {
}

// @NotNull(message = "O Campo Título é obrigatório.") TituloDTO titulo
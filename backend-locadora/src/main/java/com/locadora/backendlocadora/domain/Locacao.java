package com.locadora.backendlocadora.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record Locacao(
        @JsonProperty("_id") Long id,
        @NotNull(message = "O campo data da locação é obrigatório.") @PastOrPresent(message = "O campo data de locação não pode ser um valor futuro.") @Schema(type = "string", example = "2023-11-24", description = "Data de locação") Date dataLocacao,
        @NotNull(message = "O campo data de devolução prevista é obrigatório.") @FutureOrPresent(message = "O campo data de devolução prevista não pode ser anterior ao dia atual.") @Schema(type = "string", example = "2023-12-15", description = "Data de devolução prevista da locação.") Date dataDevolucaoPrevista,
        @FutureOrPresent(message = "O campo data de devolução efetiva não pode ser anterior ao dia atual.") @Schema(type = "string", example = "2023-12-15", description = "Data de devolução efetiva da locação") @Hidden Date dataDevolucaoEfetiva,
        @NotNull(message = "O campo valor cobrado é obrigatório.") @Positive(message = "O valor cobrado tem que ser maior que zero.") @Schema(type = "number", format = "double", example = "50", description = "Valor cobrado da locação") Double valorCobrado,
        @Positive(message = "A multa cobrada tem que ser maior que zero.") @Schema(type = "number", format = "double", example = "75", description = "Multa cobrado da locação") @Hidden Double multaCobrada,
        @NotNull(message = "O campo cliente é obrigatório.") Cliente cliente,
        @NotNull(message = "O campo item é obrigatório.") Item item) {
}
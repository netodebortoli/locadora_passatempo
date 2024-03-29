package com.locadora.backendlocadora.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente implements Serializable {

    @JsonProperty("_id")
    @Schema(type = "long", example = "1")
    protected Long id;

    @Hidden
    protected String numInscricao;

    @NotBlank(message = "O campo nome é obrigatório.")
    @Size(max = 255, message = "O tamanho máximo do campo nome é 255 caracteres.")
    @Schema(type = "string", example = "Luis Inácio", description = "Nome do cliente.")
    protected String nome;

    @NotNull(message = "O campo data de nascimento é obrigatório.")
    @PastOrPresent(message = "O campo data de nascimento não pode ser um valor futuro.")
    @Schema(type = "string", example = "2000-10-27", description = "Data de nascimento do cliente.")
    protected Date dataNascimento;

    @NotBlank(message = "O campo sexo é obrigatório.")
    @Pattern(message = "O formado do campo é inválido. O campo sexo deve ser: M, m, F ou f.", regexp = "M|m|F|f")
    @Schema(type = "string", example = "M", description = "Sexo do cliente.")
    protected String sexo;

    @Hidden
    protected String status;

}

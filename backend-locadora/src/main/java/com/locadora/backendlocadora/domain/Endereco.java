package com.locadora.backendlocadora.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Endereco(
        @JsonProperty("_id") @Schema(type = "long", example = "1") Long id,
        @NotBlank(message = "O campo logradouro é obrigatório.") @Size(max = 255, message = "O tamanho máximo do campo logradouro é 255 caracteres.") @Schema(type = "string", example = "Rua da Lama", description = "Logradouro do endereço.") String logradouro,
        @NotBlank(message = "O campo número é obrigatório.") @Size(max = 10, message = "O tamanho máximo do campo número é 10 caracteres.") @Schema(type = "string", example = "22", description = "Número do endereço.") String numero,
        @NotBlank(message = "O campo bairro é obrigatório.") @Size(max = 255, message = "O tamanho máximo do campo bairro é 255 caracteres.") @Schema(type = "string", example = "Jardim da Penha", description = "Bairro do endereço.") String bairro,
        @NotBlank(message = "O campo CEP é obrigatório.") @Size(min = 8, max = 8, message = "O CEP deve conter 8 dígitos numéricos.") @Schema(type = "string", example = "29010001", description = "CEP do endereço.") String cep,
        @NotBlank(message = "O campo localidade é obrigatório.") @Size(max = 255, message = "O tamanho máximo do campo localidade é 255 caracteres.") @Schema(type = "string", example = "Vitória", description = "Cidade do endereço.") String localidade,
        @NotBlank(message = "O campo unidade federativa (UF) é obrigatório.") @Pattern(message = "Formato do UF inválido.", regexp = "^[A-Z]{2}$") @Size(max = 2, message = "O tamanho do campo unidade federativa (UF) é de 2 caracteres.") @Schema(type = "string", example = "ES", description = "UF do endereço.") String uf) {
}
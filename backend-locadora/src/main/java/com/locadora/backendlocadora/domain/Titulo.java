package com.locadora.backendlocadora.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record Titulo(
                @JsonProperty("_id") Long id,
                @NotBlank(message = "O campo Nome é obrigatório.") @Size(message = "O tamanho máximo do Nome é de 255 caracteres.", max = 255) String nome,
                @NotBlank(message = "O campo Ano é obrigatório.") @Size(message = "O Ano informado é inválido.", max = 4, min = 4) String ano,
                @NotBlank(message = "O campo Sinopse é obrigatório.") @Size(message = "O tamanho máximo da Sinopse é de 600 caracteres.", max = 600) String sinopse,
                @NotBlank(message = "O campo Categoria é obrigatório.") String categoria,
                @NotNull(message = "O campo Diretor é obrigatório.") Diretor diretor,
                @NotNull(message = "O campo Classe é obrigatório.") Classe classe,
                @NotNull(message = "O campo Atores é obrigatório.") List<Ator> atores) {
}

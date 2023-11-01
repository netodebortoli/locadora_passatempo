package com.locadora.backendlocadora.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record Titulo(
        @JsonProperty("_id") @Hidden Long id,
        @NotBlank(message = "O campo Nome é obrigatório.") @Size(message = "O tamanho máximo do Nome é de 255 caracteres.", max = 255) @Schema(type = "string", example = "Django Livre", description = "Título do filme.") String nome,
        @NotBlank(message = "O campo Ano é obrigatório.") @Size(message = "O Ano informado é inválido.", max = 4, min = 4) @Schema(type = "string", example = "2012", description = "Ano de lançamento do título.") String ano,
        @NotBlank(message = "O campo Sinopse é obrigatório.") @Size(message = "O tamanho máximo da Sinopse é de 2000 caracteres.", max = 2000) @Schema(type = "string", example = "No sul dos Estados Unidos, o ex-escravo Django faz uma aliança inesperada com o caçador de recompensas Schultz para caçar os criminosos mais procurados do país e resgatar sua esposa de um fazendeiro que força seus escravos a participar de competições mortais.", description = "Sinopse do título.") String sinopse,
        @NotBlank(message = "O campo Categoria é obrigatório.") @Schema(type = "string", example = "Drama", description = "Categoria do título.") String categoria,
        @NotNull(message = "O campo Diretor é obrigatório.") @Schema(type = "object", example = "{ \"id\": 1 }", description = "Diretor do título.") Diretor diretor,
        @NotNull(message = "O campo Classe é obrigatório.") @Schema(type = "object", example = "{ \"id\": 1 }", description = "Classe do título.") Classe classe,
        @NotNull(message = "O campo Atores é obrigatório.") @Schema(type = "object", example = "[{ \"id\": 1 }]", description = "Atores do título.") List<Ator> atores) {
}

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
                @NotBlank(message = "O campo Nome é obrigatório.") @Size(message = "O tamanho máximo do Nome é de 255 caracteres.", max = 255) @Schema(type = "string", example = "Titanic", description = "Título do filme.") String nome,
                @NotBlank(message = "O campo Ano é obrigatório.") @Size(message = "O Ano informado é inválido.", max = 4, min = 4) @Schema(type = "string", example = "1997", description = "Ano de lançamento do título.") String ano,
                @NotBlank(message = "O campo Sinopse é obrigatório.") @Size(message = "O tamanho máximo da Sinopse é de 600 caracteres.", max = 600) @Schema(type = "string", example = "Um artista pobre e uma jovem rica se conhecem e se apaixonam na fatídica viagem inaugural do Titanic em 1912. Embora esteja noiva do arrogante herdeiro de uma siderúrgica, a jovem desafia sua família e amigos em busca do verdadeiro amor.", description = "Sinopse do título.") String sinopse,
                @NotBlank(message = "O campo Categoria é obrigatório.") @Schema(type = "string", example = "Romance", description = "Categoria do título.") String categoria,
                @NotNull(message = "O campo Diretor é obrigatório.") @Schema(type = "json", description = "Diretor do título.") Diretor diretor,
                @NotNull(message = "O campo Classe é obrigatório.") @Schema(type = "json", description = "Classe do título.") Classe classe,
                @NotNull(message = "O campo Atores é obrigatório.") @Schema(type = "json", description = "Atores do título.") List<Ator> atores) {
}

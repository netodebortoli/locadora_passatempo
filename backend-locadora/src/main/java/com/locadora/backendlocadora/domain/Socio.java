package com.locadora.backendlocadora.domain;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Socio extends Cliente<Long, Socio> {

    @NotBlank(message = "O campo CPF é obrigatório.")
    @CPF(message = "O formato do CPF é inválido.")
    @Schema(type = "string", example = "21491157003", description = "CPF do sócio.")
    private String cpf;

    @NotBlank(message = "O campo número de telefone é obrigatório.")
    @Pattern(message = "O formato do telefone é inválido.", regexp = "^[1-9]{2}(?:[2-8]|9[0-9])[0-9]{3}[0-9]{4}$")
    @Size(max = 11, min = 10, message = "O tamanho do telefone deve ser 10 ou 11 caracteres.")
    @Schema(type = "string", example = "27998237109", description = "Telefone do sócio.")
    private String telefone;

    @Valid
    @NotNull(message = "Os dados de endereco são obrigatórios.")
    @Schema(type = "json", description = "Endereço do sócio.") 
    private Endereco endereco;

    // TODO: revisar, talvez o sócio possa ter mais dependentes, porém apenas 3 ativos
    @Size(max = 3, message = "Um sócio pode ter apenas 3 dependentes.")
    @Schema(type = "json", description = "Lista de dependentes do sócio.") 
    private List<Dependente> dependentes;
}

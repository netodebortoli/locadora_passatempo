package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Socio;
import com.locadora.backendlocadora.domain.entity.SocioEntity;
import com.locadora.backendlocadora.domain.mapper.SocioMapper;
import com.locadora.backendlocadora.repository.SocioRepository;
import com.locadora.backendlocadora.service.SocioService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/socios")
@Tag(name = "Controladora de Sócios", description = "Fornece serviços REST para acesso e manipulação de dados de sócios.")
public class SocioController
        extends GenericController<Long, Socio, SocioEntity, SocioMapper, SocioRepository, SocioService> {

    @Operation(description = "Cria um objeto do tipo Sócio", responses = {
            @ApiResponse(responseCode = "201", description = "Sócio criador com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Sócio.", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Socio criarSocio(@RequestBody @Valid @NotNull Socio socio)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(socio);
    }

    @Operation(description = "Atualiza um objeto do tipo Sócio pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Sócio atualizado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Sócio.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Sócio não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{id}")
    public Socio atualizarSocio(
            @PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid @NotNull Socio socio)
            throws RegistroNaoEncontradoException, NegocioException {

        service.buscarPorId(id);

        Socio model = new Socio();
        model.setId(id);
        model.setNome(socio.getNome());
        model.setDataNascimento(socio.getDataNascimento());
        model.setSexo(socio.getSexo());
        model.setNumInscricao(socio.getNumInscricao());
        model.setCpf(socio.getCpf());
        model.setTelefone(socio.getTelefone());
        model.setEndereco(socio.getEndereco());
        model.setDependentes(socio.getDependentes());

        return service.salvar(model);

    }
}

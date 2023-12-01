package com.locadora.backendlocadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Cliente;
import com.locadora.backendlocadora.domain.Dependente;
import com.locadora.backendlocadora.domain.Socio;
import com.locadora.backendlocadora.domain.entity.SocioEntity;
import com.locadora.backendlocadora.domain.mapper.SocioMapper;
import com.locadora.backendlocadora.repository.SocioRepository;
import com.locadora.backendlocadora.service.SocioService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/socios")
@Tag(name = "Controladora de Clientes", description = "Fornece serviços REST para acesso e manipulação de dados de Clientes.")
public class SocioController
        extends GenericController<Long, Socio, SocioEntity, SocioMapper, SocioRepository, SocioService> {

    @Operation(description = "Cria um objeto do tipo Sócio", responses = {
            @ApiResponse(responseCode = "201", description = "Sócio criado com sucesso.", content = {
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

    @Operation(description = "Atualiza o status do Sócio pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Status do sócio alterado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar status do sócio.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Sócio não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Socio atualizarStatusSocio(@PathVariable @Positive @NotNull Long id,
            @Parameter(schema = @Schema(type = "string", example = "Inativo", description = "Novo status a ser alterado no sócio.")) @RequestBody @NotBlank String status) {
        return service.atualizarSocio(id, status);
    }

    @Operation(description = "Atualiza o status do Dependente pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Status do dependente alterado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar status do dependente.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Dependente não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PatchMapping("/dependentes/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Dependente atualizarStatusDependente(@PathVariable @Positive @NotNull Long id,
            @Parameter(schema = @Schema(type = "string", example = "Inativo", description = "Novo status a ser alterado no dependente.")) @RequestBody @NotBlank String status)
            throws NegocioException {
        return service.atualizarDependente(id, status);
    }

    @GetMapping("/ativos")
    public List<Cliente> listarTodosClientesAtivos() {
        return service.buscarTodosClientesAtivos();
    }

}

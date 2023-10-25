package com.locadora.backendlocadora.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.enums.Categoria;
import com.locadora.backendlocadora.domain.enums.TipoItem;

@RestController
@RequestMapping("/api/enumeradores")
@Tag(name = "Controladora de enumeradores", description = "Fornece serviços REST para acesso aos dados de enumeradores.")
public class EnumController {

    @Operation(description = "Obtem uma lista de todas as categorias registradas.", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao listar categorias.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping("/categorias")
    public String[] getCategorias() {
        return Categoria.getCategorias();
    }

    @Operation(description = "Obtem uma lista de todos os tipos de item registrados.", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao listar tipos de item.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping("/tipos-item")
    public String[] getTiposItem() {
        return TipoItem.getTiposItem();
    }

}

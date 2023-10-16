package com.locadora.backendlocadora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.enums.Categoria;
import com.locadora.backendlocadora.domain.enums.TipoItem;

@RestController
@RequestMapping("/api/enumeradores")
public class EnumController {

    @RequestMapping("/categorias")
    public String[] getCategorias() {
        return Categoria.getCategorias();
    }

    @RequestMapping("/tipos-item")
    public String[] getTiposItem() {
        return TipoItem.getTiposItem();
    }

}

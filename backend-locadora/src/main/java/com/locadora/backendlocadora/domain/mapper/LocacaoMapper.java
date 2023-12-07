package com.locadora.backendlocadora.domain.mapper;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Locacao;
import com.locadora.backendlocadora.domain.entity.LocacaoEntity;

@Component
public class LocacaoMapper extends GenericMapper<Locacao, LocacaoEntity> {

    private ItemMapper itemMapper = new ItemMapper();
    private ClienteMapper clienteMapper = new ClienteMapper();

    public Locacao toModel(LocacaoEntity entity) {

        if (entity == null) {
            return null;
        }

        return new Locacao(
                entity.getId(),
                entity.getDataLocacao(),
                entity.getDataDevolucaoPrevista(),
                entity.getDataDevolucaoEfetiva(),
                entity.getValorCobrado(),
                entity.getMultaCobrada(),
                clienteMapper.toModel(entity.getCliente()),
                itemMapper.toModel(entity.getItem()));
    }

    public LocacaoEntity toEntity(Locacao model) {

        if (model == null) {
            return null;
        }

        LocacaoEntity entity = new LocacaoEntity();

        if (model.id() != null)
            entity.setId(model.id());

        entity.setDataLocacao(model.dataLocacao());
        entity.setDataDevolucaoPrevista(model.dataDevolucaoPrevista());
        entity.setDataDevolucaoEfetiva(model.dataDevolucaoEfetiva());
        entity.setValorCobrado(model.valorCobrado());
        entity.setMultaCobrada(model.multaCobrada());
        entity.setCliente(clienteMapper.toEntity(model.cliente()));
        entity.setItem(itemMapper.toEntity(model.item()));

        return entity;
    }

}

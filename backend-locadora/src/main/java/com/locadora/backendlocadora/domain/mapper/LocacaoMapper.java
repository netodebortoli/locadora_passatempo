package com.locadora.backendlocadora.domain.mapper;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Cliente;
import com.locadora.backendlocadora.domain.Dependente;
import com.locadora.backendlocadora.domain.Locacao;
import com.locadora.backendlocadora.domain.Socio;
import com.locadora.backendlocadora.domain.entity.ClienteEntity;
import com.locadora.backendlocadora.domain.entity.DependenteEntity;
import com.locadora.backendlocadora.domain.entity.LocacaoEntity;
import com.locadora.backendlocadora.domain.entity.SocioEntity;

@Component
public class LocacaoMapper extends GenericMapper<Locacao, LocacaoEntity> {

    private ItemMapper itemMapper = new ItemMapper();
    private SocioMapper socioMapper = new SocioMapper();
    private DependenteMapper dependenteMapper = new DependenteMapper();

    public Locacao toModel(LocacaoEntity entity) {

        if (entity == null) {
            return null;
        }

        Cliente<?> cliente;

        if (entity.getCliente() instanceof SocioEntity)
            cliente = socioMapper.toModel((SocioEntity) entity.getCliente());
        else
            cliente = dependenteMapper.toModel((DependenteEntity) entity.getCliente());

        return new Locacao(
                entity.getId(),
                entity.getDataLocacao(),
                entity.getDataDevolucaoPrevista(),
                entity.getDataDevolucaoEfetiva(),
                entity.getValorCobrado(),
                entity.getMultaCobrada(),
                cliente,
                itemMapper.toModel(entity.getItem()));
    }

    public LocacaoEntity toEntity(Locacao model) {

        if (model == null) {
            return null;
        }

        LocacaoEntity entity = new LocacaoEntity();
        ClienteEntity<?> cliente;

        if (model.cliente() instanceof Socio)
            cliente = socioMapper.toEntity((Socio) model.cliente());
        else
            cliente = dependenteMapper.toEntity((Dependente) model.cliente());

        if (model.id() != null)
            entity.setId(model.id());

        entity.setDataLocacao(model.dataLocacao());
        entity.setDataDevolucaoPrevista(model.dataDevolucaoPrevista());
        entity.setDataDevolucaoEfetiva(model.dataDevolucaoEfetiva());
        entity.setValorCobrado(model.valorCobrado());
        entity.setMultaCobrada(model.multaCobrada());
        entity.setCliente(cliente);
        entity.setItem(itemMapper.toEntity(model.item()));

        return entity;
    }

}

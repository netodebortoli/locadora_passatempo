package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.service.exception.NegocioException;

public abstract class GenericMapper<M, E> {

    public abstract M toModel(E entity);

    public abstract E toEntity(M model) throws NegocioException;

}

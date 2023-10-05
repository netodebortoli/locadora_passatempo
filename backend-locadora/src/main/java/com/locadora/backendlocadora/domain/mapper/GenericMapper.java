package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.service.exception.NegocioException;

public abstract class GenericMapper<M, E> {

    public abstract M toDTO(E registro);
    public abstract E toEntity(M registro) throws NegocioException;
}

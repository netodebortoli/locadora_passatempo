package com.locadora.backendlocadora.domain.mapper;

public abstract class GenericMapper<M, E> {

    public abstract M toDTO(E registro);
    public abstract E toEntity(M registro);

}

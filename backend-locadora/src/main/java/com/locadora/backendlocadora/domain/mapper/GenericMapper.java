package com.locadora.backendlocadora.domain.mapper;

public abstract class GenericMapper<M, E> {

    public abstract M toModel(E entity);

    public abstract E toEntity(M model);

}

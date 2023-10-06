package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.TituloEntity;

@Repository
public interface TituloRepository extends JpaRepository<TituloEntity, Long> {

    //TODO: implementar método para buscar Títulos que um ator possui
}
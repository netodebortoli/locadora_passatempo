package com.locadora.backendlocadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    //TODO: implementar método para buscar Títulos que um ator possui
}
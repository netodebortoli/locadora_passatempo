package com.locadora.backendlocadora.repositories;

import com.locadora.backendlocadora.entity.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    //TODO: implementar método para buscar Títulos que um ator possui
}
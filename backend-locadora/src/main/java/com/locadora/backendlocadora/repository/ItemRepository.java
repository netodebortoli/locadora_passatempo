package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.ItemEntity;
import com.locadora.backendlocadora.domain.entity.TituloEntity;


@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    public ItemEntity findItemByNumSerie(String numSerie);

    public ItemEntity findFirstItemByTitulo(TituloEntity titulo);
}

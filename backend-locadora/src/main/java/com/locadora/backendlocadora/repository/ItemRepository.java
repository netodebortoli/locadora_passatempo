package com.locadora.backendlocadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    public ItemEntity findItemByNumSerie(String numSerie);

    @Query("From ItemEntity ORDER BY numSerie")
    public List<ItemEntity> findAll();

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM ItemEntity i "
            + " INNER JOIN LocacaoEntity l ON i.id = l.item.id WHERE l.item.id = :id")
    public boolean isItemPossuiLocacoes(@Param("id") Long id);

}

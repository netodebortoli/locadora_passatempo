package com.locadora.backendlocadora.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locadora.backendlocadora.domain.entity.LocacaoEntity;

public interface LocacaoRepository extends JpaRepository<LocacaoEntity, Long> {

    @Query("From LocacaoEntity ORDER BY dataLocacao DESC")
    public List<LocacaoEntity> findAll();

    @Query("SELECT CASE WHEN COUNT(lc) > 0 THEN true ELSE false END FROM LocacaoEntity lc" +
            " WHERE lc.cliente.id = :id AND lc.dataDevolucaoEfetiva IS null AND lc.dataDevolucaoPrevista < now() ")
    public boolean isClienteEmDebito(@Param("id") Long id);

    @Query("From LocacaoEntity WHERE item.id = :idItem AND dataDevolucaoEfetiva IS null")
    public LocacaoEntity isItemDisponivel(@Param("idItem") Long idItem);
}

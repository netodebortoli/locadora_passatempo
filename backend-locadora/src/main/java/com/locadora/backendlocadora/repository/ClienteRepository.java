package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locadora.backendlocadora.domain.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClienteEntity c "
            + " INNER JOIN LocacaoEntity l ON c.id = l.cliente.id WHERE l.cliente.id = :id AND l.dataDevolucaoEfetiva IS null")
    public boolean isClientePossuiLocacoes(@Param("id") Long id);

}

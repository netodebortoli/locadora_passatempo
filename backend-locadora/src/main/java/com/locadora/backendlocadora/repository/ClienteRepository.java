package com.locadora.backendlocadora.repository;

import com.locadora.backendlocadora.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locadora.backendlocadora.domain.entity.ClienteEntity;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClienteEntity c "
            + " INNER JOIN LocacaoEntity l ON c.id = l.cliente.id WHERE c.id = :id AND l.cliente.id = :id AND l.dataDevolucaoEfetiva IS null")
    public boolean isClientePossuiLocacoes(@Param("id") Long id);

    @Query("FROM ClienteEntity c WHERE c.status = 'ATIVO'")
    public List<ClienteEntity> findAllActiveClients();

}

package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locadora.backendlocadora.domain.entity.DependenteEntity;

public interface DependenteRepository extends JpaRepository<DependenteEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM DependenteEntity d, SocioEntity e "
            + "WHERE d.id = :id AND d.socio.id = e.id AND e.status = 'ATIVO'")
    public boolean isSocioActive(@Param("id") Long id);

}

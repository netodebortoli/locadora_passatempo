package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.DependenteEntity;
import com.locadora.backendlocadora.domain.entity.SocioEntity;

@Repository
public interface SocioRepository extends JpaRepository<SocioEntity, Long> {

    public SocioEntity findByCpf(String cpf);

    @Query("From SocioEntity WHERE id = :id")
    public SocioEntity findBySocio(@Param("id") Long id);

    @Query("From DependenteEntity WHERE id = :id")
    public DependenteEntity findByDependente(@Param("id") Long id);

}

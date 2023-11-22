package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.SocioEntity;

@Repository
public interface SocioRepository extends JpaRepository<SocioEntity, Long> {

    public SocioEntity findByCpf(String cpf);

    @Query("SELECT numInscricao from SocioEntity WHERE id = :id")
    public String findNumInscricaoBySocio(@Param("id") Long id);

    @Query("SELECT numInscricao from DependenteEntity WHERE id = :id")
    public String findNumInscricaoByDependente(@Param("id") Long id);

}

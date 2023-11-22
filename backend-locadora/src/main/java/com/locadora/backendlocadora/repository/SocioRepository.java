package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.SocioEntity;

@Repository
public interface SocioRepository extends JpaRepository<SocioEntity, Long> {

    public SocioEntity findByCpf(String cpf);

}

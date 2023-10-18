package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.ClasseEntity;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseEntity, Long> {

    @Query("from ClasseEntity WHERE lower(nome) LIKE lower(:nome)")
    public ClasseEntity findByNome(@Param("nome") String nome);

    @Query("from ClasseEntity c, TituloEntity t "
            + "WHERE c.id = t.classe.id AND c.id = :idClasse")
    public ClasseEntity findSeClasseTemTitulos(@Param("idClasse") Long idClasse);
}

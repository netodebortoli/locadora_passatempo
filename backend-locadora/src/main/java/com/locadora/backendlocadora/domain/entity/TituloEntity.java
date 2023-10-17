package com.locadora.backendlocadora.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.locadora.backendlocadora.domain.enums.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "titulos")
public class TituloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 4)
    private String ano;

    @NotBlank
    @Column(nullable = false, length = 600)
    private String sinopse;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_diretor", referencedColumnName = "id", nullable = false)
    private DiretorEntity diretor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_classe", referencedColumnName = "id", nullable = false)
    private ClasseEntity classe;

    @ManyToMany
    @JoinTable(name = "titulo_ator",
            joinColumns = @JoinColumn(name = "id_titulo"),
            inverseJoinColumns = @JoinColumn(name = "id_ator"))
    private List<AtorEntity> atores = new ArrayList<>();

}

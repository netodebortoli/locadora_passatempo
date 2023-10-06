package com.locadora.backendlocadora.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.locadora.backendlocadora.domain.enums.Categoria;
import com.locadora.backendlocadora.domain.enums.converter.CategoriaConverter;

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
    @Column(nullable = false)
    private String ano;

    @NotBlank
    @Column(nullable = false, length = 500)
    private String sinopse;

    @Column(nullable = false)
    @Convert(converter = CategoriaConverter.class)
    private Categoria categoria;

    @Transient
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_diretor", referencedColumnName = "id", nullable = false)
    private DiretorEntity diretor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_classe", referencedColumnName = "id", nullable = false)
    private ClasseEntity classe;

    @Transient
    @ManyToMany
    @JoinTable(name = "titulo_ator",
            joinColumns = @JoinColumn(name = "id_titulo"),
            inverseJoinColumns = @JoinColumn(name = "id_ator"))
    private List<AtorEntity> atores = new ArrayList<>();

}

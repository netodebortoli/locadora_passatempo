package com.locadora.backendlocadora.entity;

import com.locadora.backendlocadora.entity.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "titulos")
public class Titulo {

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
    @Column(nullable = false)
    private String sinopse;

    @Column(nullable = false)
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_diretor", referencedColumnName = "id", nullable = false)
    private Diretor diretor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_classe", referencedColumnName = "id", nullable = false)
    private Classe classe;

    @ManyToMany
    @JoinTable(name = "titulo_ator",
            joinColumns = @JoinColumn(name = "id_titulo"),
            inverseJoinColumns = @JoinColumn(name = "id_ator"))
    private List<Ator> atores;

}

package com.locadora.backendlocadora.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "atores")
public class AtorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "atores", fetch = FetchType.LAZY)
    private List<TituloEntity> titulos = new ArrayList<>();
}

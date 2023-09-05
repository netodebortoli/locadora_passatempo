package com.locadora.backendlocadora.entity;

import com.locadora.backendlocadora.entity.enums.TipoItem;
import com.locadora.backendlocadora.entity.enums.converter.TipoItemConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "num_serie", nullable = false)
    private String numSerie;

    @NotNull
    @Column(name = "tipo_item", nullable = false)
    @Convert(converter = TipoItemConverter.class)
    private TipoItem tipoItem;

    @NotNull
    @Column(name = "data_aquisicao", nullable = false)
    private Date dataAquisicao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_titulo", referencedColumnName = "id")
    private Titulo titulo;

}
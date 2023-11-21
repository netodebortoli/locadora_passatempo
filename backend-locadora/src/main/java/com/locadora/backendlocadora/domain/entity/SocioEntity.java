package com.locadora.backendlocadora.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "socios")
public class SocioEntity extends ClienteEntity {

    @Column(nullable = false)
    protected String cpf;

    @Column(nullable = false, length = 11)
    protected String telefone;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    protected EnderecoEntity endereco;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "socio")
    protected List<DependenteEntity> dependentes = new ArrayList<>();

}

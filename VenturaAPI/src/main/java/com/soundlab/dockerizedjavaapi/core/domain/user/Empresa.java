package com.soundlab.dockerizedjavaapi.core.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="empresa")
public class Empresa extends User {

    @Column(name = "razao_social")
    private String razaoSocial;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id")
    List<Vaga> vagas;
}

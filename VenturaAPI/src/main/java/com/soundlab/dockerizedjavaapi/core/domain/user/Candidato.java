package com.soundlab.dockerizedjavaapi.core.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue(value="candidato")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude={"respostas"})
@ToString(exclude={"respostas"})
public class Candidato extends User {

    @JsonBackReference
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidato_id")
    private List<Resposta> respostas;
}

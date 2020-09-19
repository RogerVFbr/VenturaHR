package com.soundlab.dockerizedjavaapi.core.user;

import com.soundlab.dockerizedjavaapi.core.resposta.Resposta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
@DiscriminatorValue(value="candidato")
public class Candidato extends User {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidato_id")
    private List<Resposta> respostas;
}

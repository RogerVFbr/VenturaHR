package com.soundlab.dockerizedjavaapi.core.resposta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soundlab.dockerizedjavaapi.core.AuditableEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "respostas")
public class Resposta extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vaga_id")
    private Long vagaId;

    @Column(name = "candidato_id")
    private Long candidatoId;

    @Column(name = "curriculo_url")
    private String curriculoUrl;

    @Column(name = "text_content")
    private String textContent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resposta_id")
    List<RespostaCriterio> respostasCriterios;

    @JsonIgnore
    public Long getVagaId() {
        return this.vagaId;
    }
}

package com.soundlab.dockerizedjavaapi.core.domain.resposta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soundlab.dockerizedjavaapi.core.AuditableEntity;
import com.soundlab.dockerizedjavaapi.core.Level;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.Criterio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "resposta_criterio")
public class RespostaCriterio extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "criterio_id")
    private Long criterioId;

    @OneToOne
    @JoinColumn(name = "criterio_id", insertable = false, updatable = false)
    private Criterio criterio;

    @Column(name = "resposta_id")
    private Long respostaId;

    @Column(name = "level")
    private Level level;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @JsonIgnore
    public Long getRespostaId() {
        return this.respostaId;
    }
}
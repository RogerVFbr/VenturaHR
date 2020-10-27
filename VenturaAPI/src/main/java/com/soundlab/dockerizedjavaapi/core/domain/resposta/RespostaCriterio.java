package com.soundlab.dockerizedjavaapi.core.domain.resposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soundlab.dockerizedjavaapi.core.AuditableEntity;
import com.soundlab.dockerizedjavaapi.core.Level;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaCriterio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "resposta_criterio")
@ToString
public class RespostaCriterio extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "criterio_id")
    @JsonProperty("criterio_id")
    private Long criterioId;

    @Column(name = "level")
    private Level level;

    @OneToOne
    @JoinColumn(name = "criterio_id", insertable = false, updatable = false)
    private VagaCriterio vagaCriterio;
}

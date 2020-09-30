package com.soundlab.dockerizedjavaapi.core.domain.vaga;

import com.soundlab.dockerizedjavaapi.core.AuditableEntity;
import com.soundlab.dockerizedjavaapi.core.Level;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "vaga_criterios")
public class VagaCriterio extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pmd")
    private Level pmd;

    @Column(name = "weight")
    private Level weight;

    @Column(name = "position")
    private int position;
}

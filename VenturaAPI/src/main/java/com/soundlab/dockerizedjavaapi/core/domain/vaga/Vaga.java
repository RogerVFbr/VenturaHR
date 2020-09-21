package com.soundlab.dockerizedjavaapi.core.domain.vaga;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.soundlab.dockerizedjavaapi.core.AuditableEntity;
import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
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
@Table(name = "vagas")
public class Vaga extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private VagaStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vaga_id")
    private List<Resposta> respostas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vaga_id")
    private List<Criterio> criterios;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @JsonProperty("respostasCount")
    public int getRespostasCount() {
        return respostas.size();
    }

    @JsonIgnore
    public Long getOwnerId() {
        return this.ownerId;
    }

    @JsonProperty("perfil")
    public Double getPerfil() {
        double somaDosPesos = criterios.stream()
            .mapToDouble(crit -> crit.getWeight().getLevelValue())
            .sum();

        double somaMultiplosPmdPeso = criterios.stream()
            .mapToDouble(crit -> crit.getPmd().getLevelValue() * crit.getWeight().getLevelValue())
            .sum();

        double result = somaMultiplosPmdPeso / somaDosPesos;

        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}

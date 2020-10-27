package com.soundlab.dockerizedjavaapi.core.domain.vaga;

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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "vagas")
@EqualsAndHashCode(callSuper = false)
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

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "type")
    private VagaType type;

    @Column(name = "time_span")
    private String timeSpan;

    @Column(name = "date_expiration")
    private LocalDateTime expirationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vaga_id", insertable = false, updatable = false)
    @OrderBy("dateCreated DESC")
    private List<Resposta> respostas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id", nullable = false)
    @OrderBy("position")
    private List<VagaCriterio> vagaCriterios;

    @JsonProperty("respostasCount")
    public int getRespostasCount() {
        if (respostas != null) return respostas.size();
        return 0;
    }

    @JsonProperty("perfil")
    public Double getPerfil() {

        if (vagaCriterios.isEmpty()) return 0D;

        double somaDosPesos = vagaCriterios.stream()
            .mapToDouble(crit -> crit.getWeight().getLevelValue())
            .sum();

        double somaMultiplosPmdPeso = vagaCriterios.stream()
            .mapToDouble(crit -> crit.getPmd().getLevelValue() * crit.getWeight().getLevelValue())
            .sum();

        double result = somaMultiplosPmdPeso / somaDosPesos;

        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}

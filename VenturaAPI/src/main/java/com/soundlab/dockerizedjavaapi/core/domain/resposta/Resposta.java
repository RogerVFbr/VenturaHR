package com.soundlab.dockerizedjavaapi.core.domain.resposta;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.soundlab.dockerizedjavaapi.core.AuditableEntity;
import com.soundlab.dockerizedjavaapi.core.domain.user.Candidato;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "respostas")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"candidato"})
@ToString(exclude = {"candidato"})
public class Resposta extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vaga_id")
    private Long vagaId;

    @Column(name = "candidato_id")
    private Long candidatoId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidato_id", insertable = false, updatable = false)
    private Candidato candidato;

    @Column(name = "curriculo_url")
    private String curriculoUrl;

    @Column(name = "text_content")
    private String textContent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "resposta_id", nullable = false)
    private List<RespostaCriterio> respostasCriterios;

    @JsonProperty("perfil_resposta")
    public Double getPerfilResposta() {
        try {
            double somaDosPesos = respostasCriterios.stream()
                .mapToDouble(
                    resposta -> resposta
                        .getVagaCriterio()
                        .getWeight()
                        .getLevelValue()
                )
                .sum();

            double somaMultiplosPmdPeso = respostasCriterios.stream()
                .mapToDouble(
                    resposta ->
                        resposta
                            .getLevel()
                            .getLevelValue()
                            *
                            resposta
                                .getVagaCriterio()
                                .getWeight()
                                .getLevelValue()
                )
                .sum();

            double result = somaMultiplosPmdPeso / somaDosPesos;

            BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
            return bd.doubleValue();

        } catch (Exception e) {
            return 0D;
        }
    }
}

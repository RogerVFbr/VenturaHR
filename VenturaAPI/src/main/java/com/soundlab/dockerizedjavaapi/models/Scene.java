package com.soundlab.dockerizedjavaapi.models;

import com.soundlab.dockerizedjavaapi.exceptions.UnableToUndoException;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import static com.soundlab.dockerizedjavaapi.models.Enums.SceneStatus.PENDENTE;
import static com.soundlab.dockerizedjavaapi.models.Enums.SceneStatus.PREPARADA;

@Data
@Entity
@Table(name = "scenes")
@ApiModel(description = "Utilizado no retorno dos endpoints GET, fornece detalhes completos da cena.")
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scene_id")
    @ApiModelProperty(notes = "Identificador gerado pelo banco de dados.")
    private Long sceneId;

    @ApiModelProperty(notes = "Título da cena.")
    private String title;

    @Column(name = "scene_update_time")
    @ApiModelProperty(notes = "Horário de atualização da cena em formato 'yyyy-MM-ddTHH:mm'.")
    private Timestamp sceneUpdateTime;

    @Column(name = "date_modified")
    @ApiModelProperty(notes = "Horário de em que atualização de cena foi salva no banco em formato 'yyyy-MM-ddTHH:mm'.")
    private Timestamp dateModified;

    @Column(name = "current_status")
    @ApiModelProperty(notes = "Status atual da cena ('PENDENTE', 'PREPARADA', 'GRAVADA', 'PENDURADA').")
    private Enums.SceneStatus currentStatus;

    public static Enums.SceneStatus revertSceneStatus(Long id, Enums.SceneStatus status) {
        switch(status) {
            case GRAVADA:
                return PREPARADA;
            case PENDURADA:
                return PREPARADA;
            case PREPARADA:
                return PENDENTE;
            default:
                throw new UnableToUndoException(id, status);
        }
    }
}

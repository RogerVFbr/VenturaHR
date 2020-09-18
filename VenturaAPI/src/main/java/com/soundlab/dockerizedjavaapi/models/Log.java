package com.soundlab.dockerizedjavaapi.models;

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

@Data
@Entity
@Table(name = "scenes_log")
@ApiModel(description = "Utilizado no retorno do endpoint GET, fornece detalhes completos dos logs.")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    @ApiModelProperty(notes = "Identificador gerado pelo banco de dados.")
    private Long logId;

    @Column(name = "scene_id")
    @ApiModelProperty(notes = "Identificador da cena.")
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

    public static Log getLogFromScene(Scene scene) {
        Log log = new Log();
        log.setTitle(scene.getTitle());
        log.setCurrentStatus(scene.getCurrentStatus());
        log.setDateModified(scene.getDateModified());
        log.setSceneUpdateTime(scene.getSceneUpdateTime());
        log.setSceneId(scene.getSceneId());
        return log;
    }
}

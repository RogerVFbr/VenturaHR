package com.soundlab.dockerizedjavaapi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Utilizado no endpoint de atualização de estado.")
public class SceneUpdate {

    @ApiModelProperty(notes = "Identificador da cena a ser atualizada.")
    private Long sceneId;

    @ApiModelProperty(notes = "Novo estado para o qual a cena deve ser atualizada.")
    private Enums.SceneStatus currentStatus;

    @ApiModelProperty(notes = "Horário de atualização da cena no formato 'yyyy-MM-ddTHH:mm'.")
    private String sceneUpdateTime;
}

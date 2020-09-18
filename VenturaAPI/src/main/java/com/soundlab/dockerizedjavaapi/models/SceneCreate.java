package com.soundlab.dockerizedjavaapi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Utilizado no endpoint de criação de cena.")
public class SceneCreate {

    @ApiModelProperty(notes = "Título da cena")
    private String title;

    @ApiModelProperty(notes = "Horário de atualização da cena no formato 'yyyy-MM-ddTHH:mm'.")
    private String sceneUpdateTime;
}

package com.soundlab.dockerizedjavaapi.controllers;

import com.soundlab.dockerizedjavaapi.controllers.validation.SceneControllerValidation;
import com.soundlab.dockerizedjavaapi.exceptions.InvalidDateTimeFormatException;
import com.soundlab.dockerizedjavaapi.exceptions.SceneNotFoundException;
import com.soundlab.dockerizedjavaapi.models.Enums;
import com.soundlab.dockerizedjavaapi.models.Log;
import com.soundlab.dockerizedjavaapi.models.Scene;
import com.soundlab.dockerizedjavaapi.models.SceneCreate;
import com.soundlab.dockerizedjavaapi.models.SceneUpdate;
import com.soundlab.dockerizedjavaapi.repositories.LogRepository;
import com.soundlab.dockerizedjavaapi.repositories.SceneRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Sistema de Gerenciamento de Cenas", description = "Operações gerais de modificação do banco de dados de cenas;")
public class SceneController {

    private final SceneRepository sceneRepo;
    private final LogRepository logRepo;

    SceneController(SceneRepository sceneRepo, LogRepository logRepo) {
        this.sceneRepo = sceneRepo;
        this.logRepo = logRepo;
    }

    @GetMapping("/scenes")
    @ApiOperation(value = "Retorna uma lista de cenas atualizadas com as últimas modificações.")
    List<Scene> getAllScenes() {
        return sceneRepo.findAll();
    }

    @GetMapping("/scenes/{id}")
    @ApiOperation(value = "Retorna uma cena de acordo com o Id enviado.")
    Scene getSceneById(@ApiParam(value = "Identificador da cena.", required = true) @PathVariable long id) {
        return sceneRepo.findById(id)
            .orElseThrow(() -> new SceneNotFoundException(id));
    }

    @PostMapping("/scenes")
    @ApiOperation(value = "Adiciona uma nova cena ao banco de dados.")
    @Transactional
    Scene createScene(@ApiParam(value = "Objeto de criação de cena. Parâmetro 'sceneUpdateTime' deve ser uma String" +
            " com formato 'yyyy-MM-ddTHH:mm'.", required = true) @RequestBody SceneCreate createdScene) {
        LocalDateTime sceneTime = SceneControllerValidation.parseDateTimeFormat(createdScene.getSceneUpdateTime()).withNano(0);
        if(sceneTime == null) throw new InvalidDateTimeFormatException(createdScene.getSceneUpdateTime());

        Scene scene = new Scene();
        scene.setCurrentStatus(Enums.SceneStatus.PENDENTE);
        scene.setSceneUpdateTime(Timestamp.valueOf(sceneTime));
        scene.setDateModified(new Timestamp(System.currentTimeMillis()));
        scene.setTitle(createdScene.getTitle());
        Scene savedScene = sceneRepo.save(scene);

        Log sceneLog = Log.getLogFromScene(savedScene);
        logRepo.save(sceneLog);
        return savedScene;
    }

    @PutMapping("/scenes/{id}")
    @Transactional
    @ApiOperation(value = "Atualiza o estado de uma cena.")
    Scene updateScene(
            @ApiParam(value = "Objeto de atualização de cena. Parâmetro 'sceneUpdateTime' deve ser uma String com " +
                    "formato 'yyyy-MM-ddTHH:mm'.", required = true) @RequestBody SceneUpdate updatedScene,
            @ApiParam(value = "Identificador da cena a ser atualizada.", required = true) @PathVariable long id) {
        return sceneRepo.findById(id)
            .map(scene -> {
                SceneControllerValidation.validateStatusUpdate(id, scene.getCurrentStatus(),
                    updatedScene.getCurrentStatus());

                SceneControllerValidation.validateUpdateTime(id, updatedScene.getSceneUpdateTime(),
                        scene.getDateModified());

                LocalDateTime sceneTime = SceneControllerValidation.parseDateTimeFormat(updatedScene.getSceneUpdateTime());
                if(sceneTime == null) throw new InvalidDateTimeFormatException(updatedScene.getSceneUpdateTime());

                scene.setSceneUpdateTime(Timestamp.valueOf(sceneTime));
                scene.setCurrentStatus(updatedScene.getCurrentStatus());
                scene.setDateModified(new Timestamp(System.currentTimeMillis()));
                Scene savedScene = sceneRepo.save(scene);

                Log sceneLog = Log.getLogFromScene(savedScene);
                logRepo.save(sceneLog);
                return savedScene;
            })
            .orElseThrow(() -> new SceneNotFoundException(id));
    }

    @PutMapping("/scenes/undo/{id}")
    @Transactional
    @ApiOperation(value = "Reverte o estado de uma cena.")
    Scene undoUpdateScene(@ApiParam(value = "Identificador da cena.", required = true) @PathVariable long id) {
        return sceneRepo.findById(id)
            .map(scene -> {
                SceneControllerValidation.validateUndoStatusUpdate(id, scene.getCurrentStatus(),
                        scene.getDateModified());

                Enums.SceneStatus revertedStatus = Scene.revertSceneStatus(id, scene.getCurrentStatus());

                List<Log> logs = logRepo.findLogsBySceneId(id).stream()
                        .filter(x -> x.getCurrentStatus().equals(revertedStatus)).collect(Collectors.toList());
                Log lastState = logs.get(logs.size()-1);

                scene.setCurrentStatus(lastState.getCurrentStatus());
                scene.setSceneUpdateTime(lastState.getSceneUpdateTime());
                scene.setDateModified(new Timestamp(System.currentTimeMillis()));
                Scene savedScene = sceneRepo.save(scene);

                Log sceneLog = Log.getLogFromScene(savedScene);
                logRepo.save(sceneLog);
                return savedScene;
            })
            .orElseThrow(() -> new SceneNotFoundException(id));
    }

    @DeleteMapping("/scenes/{id}")
    @ApiOperation(value = "Apaga uma cena.")
    void deleteScene(@ApiParam(value = "Identificador da cena.", required = true) @PathVariable long id) {
        sceneRepo.deleteById(id);
    }
}

package com.soundlab.dockerizedjavaapi;

import com.soundlab.dockerizedjavaapi.models.Enums;
import com.soundlab.dockerizedjavaapi.models.Scene;
import com.soundlab.dockerizedjavaapi.repositories.SceneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = DockerizedJavaApiApplication.class)
class SceneRepositoryIntegrationTests {

    @Autowired
    private SceneRepository sceneRepository;

    @Test
    void whenFindAll_thenReturnSome() {
        List<Scene> scenes = sceneRepository.findAll();
        assertThat(scenes).isNotNull();
        assertThat(scenes.size()).isGreaterThan(0);
    }

    @Test
    void whenFindById_thenReturnScene() {
        Scene scene = sceneRepository.findById(1L).orElse(null);
        System.out.println(scene);
        assertThat(scene).isNotNull();
        assertThat(1L).isEqualTo(scene.getSceneId());
    }

    @Test
    void whenSave_thenSceneExists() {
        Scene scene = new Scene();
        scene.setTitle("TestScene");
        scene.setCurrentStatus(Enums.SceneStatus.PENDENTE);
        scene.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
        scene.setSceneUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        Scene foundScene = sceneRepository.save(scene);
        assertThat(foundScene).isNotNull();
        assertThat(foundScene.getTitle()).isEqualTo(scene.getTitle());
        assertThat(foundScene.getDateModified().toLocalDateTime().withNano(3))
                .isEqualTo(scene.getDateModified().toLocalDateTime().withNano(3));
        assertThat(foundScene.getCurrentStatus()).isEqualTo(scene.getCurrentStatus());
    }

    @Test
    void whenUpdate_thenSceneUpdates() {
        Enums.SceneStatus updatedStatus = Enums.SceneStatus.PREPARADA;
        Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis()+10000);

        Scene updatedScene = sceneRepository.findById(1L)
                .map(scene -> {
                    scene.setCurrentStatus(updatedStatus);
                    scene.setDateModified(updatedTimestamp);
                    return sceneRepository.save(scene);
                }).orElse(null);

        assertThat(updatedScene).isNotNull();
        assertThat(updatedScene.getCurrentStatus()).isEqualTo(updatedStatus);
        assertThat(updatedScene.getDateModified()).isEqualTo(updatedTimestamp);
    }

    @Test
    void whenDelete_thenSceneVanishes() {
        Scene scene = new Scene();
        scene.setTitle("TestSceneDelete");
        scene.setCurrentStatus(Enums.SceneStatus.PENDENTE);
        scene.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
        scene.setSceneUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        Scene savedScene = sceneRepository.save(scene);
        sceneRepository.delete(savedScene);
        Scene deletedScene = sceneRepository.findById(savedScene.getSceneId()).orElse(null);
        assertThat(deletedScene).isNull();
    }
}

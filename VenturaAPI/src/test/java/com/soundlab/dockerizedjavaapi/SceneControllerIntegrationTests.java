package com.soundlab.dockerizedjavaapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soundlab.dockerizedjavaapi.controllers.SceneController;
import com.soundlab.dockerizedjavaapi.models.Enums;
import com.soundlab.dockerizedjavaapi.models.Log;
import com.soundlab.dockerizedjavaapi.models.Scene;
import com.soundlab.dockerizedjavaapi.repositories.LogRepository;
import com.soundlab.dockerizedjavaapi.repositories.SceneRepository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DockerizedJavaApiApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SceneControllerIntegrationTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;
    @Autowired private SceneController controller;
    @Autowired private SceneRepository sceneRepo;
    @Autowired private LogRepository logRepo;
    private String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

    @Test
    @Order(1)
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    @Order(2)
    public void whenGetScenes_returnInitialScenes() throws Exception{
        MvcResult result = mockMvc.perform(get("/scenes")).andExpect(status().isOk()).andReturn();
        List<Scene> scenes = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Scene>>(){});
        assertThat(scenes.size()).isEqualTo(10);
    }

    @Test
    @Order(3)
    public void whenGetSceneById_returnProperScene() throws Exception{
        MvcResult result = mockMvc.perform(get("/scenes/1")).andExpect(status().isOk()).andReturn();
        Scene scene = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Scene>(){});
        assertThat(scene.getTitle()).isEqualTo("Fatima encontra Otto");
    }

    @Test
    @Order(4)
    public void mockDataToBeUpdated() throws Exception{
        Scene pendente = new Scene();
        pendente.setSceneId(11L);
        pendente.setTitle("PendenteParaPreparada");
        pendente.setCurrentStatus(Enums.SceneStatus.PENDENTE);
        pendente.setSceneUpdateTime(Timestamp.valueOf(LocalDateTime.now().minusMinutes(2)));
        pendente.setDateModified(Timestamp.valueOf(LocalDateTime.now().minusMinutes(2)));
        Scene savedScene = sceneRepo.save(pendente);
        logRepo.save(Log.getLogFromScene(savedScene));
        assertThat(savedScene).isEqualTo(pendente);

        Scene preparadaA = new Scene();
        preparadaA.setSceneId(12L);
        preparadaA.setTitle("PreparadaParaGravada");
        preparadaA.setCurrentStatus(Enums.SceneStatus.PREPARADA);
        preparadaA.setSceneUpdateTime(Timestamp.valueOf(LocalDateTime.now().minusMinutes(2)));
        preparadaA.setDateModified(Timestamp.valueOf(LocalDateTime.now().minusMinutes(2)));
        savedScene = sceneRepo.save(preparadaA);
        logRepo.save(Log.getLogFromScene(savedScene));
        assertThat(savedScene).isEqualTo(preparadaA);

        Scene preparadaB = new Scene();
        preparadaB.setSceneId(13L);
        preparadaB.setTitle("PreparadaParaPendurada");
        preparadaB.setCurrentStatus(Enums.SceneStatus.PREPARADA);
        preparadaB.setSceneUpdateTime(Timestamp.valueOf(LocalDateTime.now().minusMinutes(2)));
        preparadaB.setDateModified(Timestamp.valueOf(LocalDateTime.now().minusMinutes(2)));
        savedScene = sceneRepo.save(preparadaB);
        logRepo.save(Log.getLogFromScene(savedScene));
        assertThat(savedScene).isEqualTo(preparadaB);
    }

    @Test
    @Order(5)
    public void whenUpdatePendenteToPreparada_execute() throws Exception{
        MvcResult result = mockMvc.perform(put("/scenes/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PREPARADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isOk()).andReturn();
        Scene scene = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Scene>(){});
        assertThat(scene.getCurrentStatus()).isEqualTo(Enums.SceneStatus.PREPARADA);
    }

    @Test
    @Order(6)
    public void whenUpdatePreparadaToGravada_execute() throws Exception{
        MvcResult result = mockMvc.perform(put("/scenes/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"GRAVADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isOk()).andReturn();
        Scene scene = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Scene>(){});
        assertThat(scene.getCurrentStatus()).isEqualTo(Enums.SceneStatus.GRAVADA);
    }

    @Test
    @Order(7)
    public void whenUpdatePreparadaToPendurada_execute() throws Exception{
        MvcResult result = mockMvc.perform(put("/scenes/13")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDURADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isOk()).andReturn();
        Scene scene = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Scene>(){});
        assertThat(scene.getCurrentStatus()).isEqualTo(Enums.SceneStatus.PENDURADA);
    }

    @Test
    @Order(8)
    public void whenUpdatePendenteToGravada_fail() throws Exception{
        mockMvc.perform(put("/scenes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"GRAVADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(9)
    public void whenUpdatePendenteToPendurada_fail() throws Exception{
        mockMvc.perform(put("/scenes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDURADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(10)
    public void whenUpdatePendenteToPendente_fail() throws Exception{
        mockMvc.perform(put("/scenes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDENTE\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(11)
    public void whenUpdatePreparadaToPendente_fail() throws Exception{
        mockMvc.perform(put("/scenes/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDENTE\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(12)
    public void whenUpdatePreparadaToPreparada_fail() throws Exception{
        mockMvc.perform(put("/scenes/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PREPARADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(13)
    public void whenUpdateGravadaToPendente_fail() throws Exception{
        mockMvc.perform(put("/scenes/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDENTE\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(14)
    public void whenUpdateGravadaToPendurada_fail() throws Exception{
        mockMvc.perform(put("/scenes/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDURADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(15)
    public void whenUpdateGravadaToPreparada_fail() throws Exception{
        mockMvc.perform(put("/scenes/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PREPARADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(16)
    public void whenUpdateGravadaToGravada_fail() throws Exception{
        mockMvc.perform(put("/scenes/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"GRAVADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(17)
    public void whenUpdatePenduradaToPendente_fail() throws Exception{
        mockMvc.perform(put("/scenes/13")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDENTE\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(18)
    public void whenUpdatePenduradaToPreparada_fail() throws Exception{
        mockMvc.perform(put("/scenes/13")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PREPARADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(19)
    public void whenUpdatePenduradaToGravada_fail() throws Exception{
        mockMvc.perform(put("/scenes/13")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"GRAVADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(20)
    public void whenUpdatePenduradaToPendurada_fail() throws Exception{
        mockMvc.perform(put("/scenes/13")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PENDURADA\", \"sceneUpdateTime\":\"" + currentDateTime + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(21)
    public void whenUpdateInFuture_fail() throws Exception{
        String updateDateTime = LocalDateTime.now().plusMinutes(5)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        mockMvc.perform(put("/scenes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PREPARADA\", \"sceneUpdateTime\":\"" + updateDateTime + "\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(22)
    public void whenUpdateBeforeLastUpdate_fail() throws Exception{
        String updateDateTime = LocalDateTime.now().minusMinutes(15)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        mockMvc.perform(put("/scenes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentStatus\": \"PREPARADA\", \"sceneUpdateTime\":\"" + updateDateTime + "\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(23)
    public void whenUndoPreparada_revertsToPendente() throws Exception{
        MvcResult result = mockMvc.perform(put("/scenes/undo/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Scene scene = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Scene>(){});
        assertThat(scene.getCurrentStatus()).isEqualTo(Enums.SceneStatus.PENDENTE);
    }

    @Test
    @Order(24)
    public void whenUndoGravada_revertsToPreparada() throws Exception{
        MvcResult result = mockMvc.perform(put("/scenes/undo/12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Scene scene = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Scene>(){});
        assertThat(scene.getCurrentStatus()).isEqualTo(Enums.SceneStatus.PREPARADA);
    }

    @Test
    @Order(25)
    public void whenUndoPendurada_revertsToPreparada() throws Exception{
        MvcResult result = mockMvc.perform(put("/scenes/undo/13")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Scene scene = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Scene>(){});
        assertThat(scene.getCurrentStatus()).isEqualTo(Enums.SceneStatus.PREPARADA);
    }

    @Test
    @Order(26)
    public void whenUndoPendente_fail() throws Exception{
        mockMvc.perform(put("/scenes/undo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(27)
    public void whenDeleteScene_sceneDisappears() throws Exception{
        mockMvc.perform(delete("/scenes/11")).andExpect(status().isOk());
        mockMvc.perform(delete("/scenes/12")).andExpect(status().isOk());
        mockMvc.perform(delete("/scenes/13")).andExpect(status().isOk());
        mockMvc.perform(get("/scenes/11")).andExpect(status().isNotFound());
        mockMvc.perform(get("/scenes/12")).andExpect(status().isNotFound());
        mockMvc.perform(get("/scenes/13")).andExpect(status().isNotFound());
    }
}

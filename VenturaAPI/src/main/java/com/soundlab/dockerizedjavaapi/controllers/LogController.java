package com.soundlab.dockerizedjavaapi.controllers;

import com.soundlab.dockerizedjavaapi.models.Log;
import com.soundlab.dockerizedjavaapi.repositories.LogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "API Log Storage", description = "Repsonsável pelo armazenamento do histórico de logs da API.")
public class LogController {

    private final LogRepository logRepo;

    public LogController(LogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @GetMapping("/logs/{id}")
    @ApiOperation(value = "Retorna uma lista com os logs de aualização de uma determinada cena.")
    List<Log> getAllLogs(@ApiParam(value = "Identificador da cena.", required = true) @PathVariable long id) {
        return logRepo.findLogsBySceneId(id);
    }
}

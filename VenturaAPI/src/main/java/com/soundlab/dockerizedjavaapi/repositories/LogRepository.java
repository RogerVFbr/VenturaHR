package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.models.Enums;
import com.soundlab.dockerizedjavaapi.models.Log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    @Query(value = "SELECT * FROM scenes_log s WHERE s.scene_id=?1", nativeQuery = true)
    List<Log> findLogsBySceneId(long id);
}

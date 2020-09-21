package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.user.Candidato;
import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.core.domain.user.UserType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<User> findByUserType(UserType userType);
//
//    default List<Candidato> findAllCandidates() {
//        List<User> users = findByUserType(UserType.CANDIDATO);
//        return users.stream()
//            .map(user ->
//                new Candidato(
//                    user.getId(),
//                    user.getName(),
//                    user.getDocumentId(),
//                    user.getDateCreated(),
//                    user.getDateModified()
//                )
//            )
//            .collect(Collectors.toList());
//    }
}

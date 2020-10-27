package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    <T> List<T> findByExpirationDateIsAfter(LocalDateTime expirationDate, Class<T> type);

    <T> List<T> findTop10ByExpirationDateIsAfterOrderByDateCreatedDesc(LocalDateTime expirationDate, Class<T> type);

    <T> List<T> findByRespostas_candidatoIdEqualsOrderByExpirationDateDesc(Long userId, Class<T> type);

    <T> List<T> findByOwnerId(Long userId, Class<T> type);

    <T> T findById(Long id, Class<T> type);

    @Query("SELECT v FROM Vaga v WHERE " +
        "v.expirationDate > :currentDate AND LOWER(v.shortDescription) LIKE LOWER(CONCAT('%', :search, '%')) " +
        "OR v.expirationDate > :currentDate AND LOWER(v.longDescription) LIKE LOWER(CONCAT('%', :search, '%')) " +
        "ORDER BY v.dateCreated DESC")
    <T> List<T> findContaining(LocalDateTime currentDate, String search, Class<T> type);

    <T> List<T> findByExpirationDateAfterAndShortDescriptionContainsIgnoreCaseOrExpirationDateAfterAndLongDescriptionContainsIgnoreCaseOrderByDateCreatedDesc(
                                                                       LocalDateTime currentDate1,
                                                                       String search1,
                                                                       LocalDateTime currentDate2,
                                                                       String search2,
                                                                       Class<T> type);

    default <T> List<T> findActiveContainingAll(String search, Class<T> type) {
        return findByExpirationDateAfterAndShortDescriptionContainsIgnoreCaseOrExpirationDateAfterAndLongDescriptionContainsIgnoreCaseOrderByDateCreatedDesc(
            LocalDateTime.now(),
            search,
            LocalDateTime.now(),
            search,
            type);
    }

    default <T> List<T> findActiveContainingAny(String search, Class<T> type) {
        List<String> params = Arrays.asList(search.split(" "));
        Set<T> result = new HashSet<>();
        params.forEach(param ->
            result
                .addAll(
                    findByExpirationDateAfterAndShortDescriptionContainsIgnoreCaseOrExpirationDateAfterAndLongDescriptionContainsIgnoreCaseOrderByDateCreatedDesc(
                        LocalDateTime.now(),
                        param,
                        LocalDateTime.now(),
                        param,
                        type)
                )
        );
        return new ArrayList<>(result);
    }

    default <T> List<T> findActiveContainingNone(String search, Class<T> type) {
        List<T> vagasAll = findByExpirationDateIsAfter(LocalDateTime.now(), type);
        List<T> vagasContaining = findActiveContainingAny(search, type);
        return vagasAll
            .stream()
            .filter(vaga -> !vagasContaining.contains(vaga))
            .collect(Collectors.toList());
    }
}


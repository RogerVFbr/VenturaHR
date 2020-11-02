package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.core.view.home.SearchType;
import com.soundlab.dockerizedjavaapi.repositories.VagaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VagaService extends GenericService<VagaRepository, Vaga> {

    public VagaService(VagaRepository vagaRepository) {
        super(vagaRepository);
    }

    public <T> List<T> listLatestAvailable(Class<T> type) {
        return repository.findTop8ByExpirationDateIsAfterOrderByDateCreatedDesc(
            LocalDateTime.now(), type);
    }

    public <T> List<T> listByCandidateAnswers(Long userId, Class<T> type) {
        return repository.findByRespostas_candidatoIdEqualsOrderByExpirationDateDesc(userId, type);
    }

    public <T> List<T> listByOwner(Long userId, Class<T> type) {
        return repository.findByOwnerId(userId, type);
    }

    public <T> T findById(Long id, Class<T> type) {
        return repository.findById(id, type);
    }

    public <T> List<T> listByInclusionType(String search, SearchType searchType, Class<T> type) {
        switch (searchType) {
            case ALL:
                return repository.findActiveContainingAll(search, type);
            case ANY:
                return repository.findActiveContainingAny(search, type);
            case NONE:
                return repository.findActiveContainingNone(search, type);
            default:
                return null;
        }
    }
}

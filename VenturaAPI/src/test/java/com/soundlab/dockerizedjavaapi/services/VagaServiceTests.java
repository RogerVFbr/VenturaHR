package com.soundlab.dockerizedjavaapi.services;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.core.view.home.SearchType;
import com.soundlab.dockerizedjavaapi.repositories.VagaRepository;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VagaServiceTests {

    @InjectMocks
    private VagaService vagaService;

    @Mock
    private VagaRepository vagaRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenListLatestAvailable_thenShouldReturnExpectedList() {
        List<Vaga> vagas = Collections.singletonList(new Vaga());

        Mockito.doReturn(vagas)
            .when(vagaRepository)
            .findTop8ByExpirationDateIsAfterOrderByDateCreatedDesc(Mockito.any(), Mockito.any());

        List<Vaga> result = vagaService.listLatestAvailable(Vaga.class);

        assertEquals(result, vagas);
    }

    @Test
    void whenListByCandidateAnswers_thenShouldReturnExpectedList() {
        List<Vaga> vagas = Collections.singletonList(new Vaga());

        Mockito.doReturn(vagas)
            .when(vagaRepository)
            .findByRespostas_candidatoIdEqualsOrderByExpirationDateDesc(Mockito.any(), Mockito.any());

        List<Vaga> result = vagaService.listByCandidateAnswers(111L, Vaga.class);

        assertEquals(result, vagas);
    }

    @Test
    void whenListByOwner_thenShouldReturnExpectedList() {
        List<Vaga> vagas = Collections.singletonList(new Vaga());

        Mockito.doReturn(vagas)
            .when(vagaRepository)
            .findByOwnerId(Mockito.any(), Mockito.any());

        List<Vaga> result = vagaService.listByOwner(1111L, Vaga.class);

        assertEquals(result, vagas);
    }

    @Test
    void whenFindById_thenShouldReturnExpectedList() {
        Vaga vaga = new Vaga();

        Mockito.doReturn(vaga)
            .when(vagaRepository)
            .findById(Mockito.any(), Mockito.any());

        Vaga result = vagaService.findById(1111L, Vaga.class);

        assertEquals(result, vaga);
    }

    static Stream<Arguments> searchTypes() {
        return Stream.of(Arguments.of(SearchType.ALL),
            Arguments.of(SearchType.ANY),
            Arguments.of(SearchType.NONE));
    }

    @ParameterizedTest
    @MethodSource("searchTypes")
    void whenListByInclusionType_thenShouldReturnExpectedList(SearchType searchType) {
        Vaga vagaAll = new Vaga();
        vagaAll.setOwnerId(111L);
        Vaga vagaAny = new Vaga();
        vagaAny.setOwnerId(222L);
        Vaga vagaNone = new Vaga();
        vagaNone.setOwnerId(333L);

        Mockito.doReturn(Collections.singletonList(vagaAll))
            .when(vagaRepository)
            .findActiveContainingAll(Mockito.anyString(), Mockito.any());

        Mockito.doReturn(Collections.singletonList(vagaAny))
            .when(vagaRepository)
            .findActiveContainingAny(Mockito.anyString(), Mockito.any());

        Mockito.doReturn(Collections.singletonList(vagaNone))
            .when(vagaRepository)
            .findActiveContainingNone(Mockito.anyString(), Mockito.any());

        List<Vaga> result = vagaService.listByInclusionType("mockSearchString", searchType, Vaga.class);

        switch (searchType) {
            case ALL:
                assertEquals(vagaAll.getOwnerId(), result.get(0).getOwnerId());
                break;
            case ANY:
                assertEquals(vagaAny.getOwnerId(), result.get(0).getOwnerId());
                break;
            case NONE:
                assertEquals(vagaNone.getOwnerId(), result.get(0).getOwnerId());
                break;
            default:
                break;
        }
    }
}

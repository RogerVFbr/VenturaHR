package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewRequestSearch;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.view.HomeCandidatoViewService;
import com.soundlab.dockerizedjavaapi.services.view.HomeEmpresaViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
    name = "View: Home",
    description = "Gerencia informações de view."
)
public class HomeViewController {

    HomeCandidatoViewService homeCandidatoViewService;
    HomeEmpresaViewService homeEmpresaViewService;

    public HomeViewController(HomeCandidatoViewService homeCandidatoViewService,
                              HomeEmpresaViewService homeEmpresaViewService) {
        this.homeCandidatoViewService = homeCandidatoViewService;
        this.homeEmpresaViewService = homeEmpresaViewService;
    }

    @GetMapping("/home/candidato/content/{id}")
    public ResponseEntity<HomeViewResponseContent> getCandidatoContent(@PathVariable long id) {
        return ResponseEntity.ok(homeCandidatoViewService.getContent(id));
    }

    @PostMapping("/home/candidato/content/search/{id}")
    public ResponseEntity<HomeViewResponseContent> getCandidatoContent(@RequestBody HomeViewRequestSearch search,
                                                                       @PathVariable long id) {
        return ResponseEntity.ok(homeCandidatoViewService.getContent(id, search));
    }

    @GetMapping("/home/empresa/content/{id}")
    public ResponseEntity<HomeViewResponseContent> getEmpresaContent(@PathVariable long id) {
        return ResponseEntity.ok(homeEmpresaViewService.getContent(id));
    }

    @PostMapping("/home/empresa/content/search/{id}")
    public ResponseEntity<HomeViewResponseContent> getEmpresaContent(@RequestBody HomeViewRequestSearch search,
                                                                     @PathVariable long id) {
        return ResponseEntity.ok(homeEmpresaViewService.getContent(id, search));
    }
}

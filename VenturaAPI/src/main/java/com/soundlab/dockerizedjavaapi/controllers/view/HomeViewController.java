package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewRequestSearch;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.view.HomeCandidatoViewService;
import com.soundlab.dockerizedjavaapi.services.view.HomeEmpresaViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @GetMapping("/home/candidato/content/search/{id}")
    public ResponseEntity<HomeViewResponseContent> getCandidatoContent(@RequestBody HomeViewRequestSearch search,
                                                                       @PathVariable long id) {
        return ResponseEntity.ok(homeCandidatoViewService.getContent(id, search));
    }

    @GetMapping("/home/empresa/content/{id}")
    public ResponseEntity<HomeViewResponseContent> getEmpresaContent(@PathVariable long id) {
        return ResponseEntity.ok(homeEmpresaViewService.getContent(id));
    }

    @GetMapping("/home/empresa/content/search/{id}")
    public ResponseEntity<HomeViewResponseContent> getEmpresaContent(@RequestBody HomeViewRequestSearch search,
                                                                     @PathVariable long id) {
        return ResponseEntity.ok(homeEmpresaViewService.getContent(id, search));
    }
}

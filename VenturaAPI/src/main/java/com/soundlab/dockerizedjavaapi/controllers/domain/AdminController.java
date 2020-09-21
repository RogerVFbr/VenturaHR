package com.soundlab.dockerizedjavaapi.controllers.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.Admin;
import com.soundlab.dockerizedjavaapi.services.domain.AdminService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }


    @GetMapping("/users/admins")
    List<Admin> findAll() {
        return adminService.findAll();
    }

    @GetMapping("/users/admins/{id}")
    Admin getById(@PathVariable long id) {
        return adminService.findById(id);
    }

    @PostMapping("/users/admins")
    Admin save(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @PutMapping("/users/admins/{id}")
    Admin update(@RequestBody Admin admin, @PathVariable long id) {
        return adminService.update(admin);
    }

    @DeleteMapping("/users/admins/{id}")
    void delete(@PathVariable long id) {
        adminService.delete(id);
    }
}

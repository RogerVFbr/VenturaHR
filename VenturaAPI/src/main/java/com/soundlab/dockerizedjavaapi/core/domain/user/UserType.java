package com.soundlab.dockerizedjavaapi.core.domain.user;

public enum UserType implements IUserType{
    CANDIDATO(0, "candidato") {
        @Override
        public User getInstance() {
            return new Candidato();
        }
    },
    EMPRESA(1, "empresa") {
        @Override
        public User getInstance() {
            return new Empresa();
        }
    },
    ADMINISTRADOR(2, "administrador") {
        @Override
        public User getInstance() {
            return new Admin();
        }
    };

    private final int id;
    private final String description;

    UserType(int id, String description) {
        this.id = id;
        this.description = description;
    }

}

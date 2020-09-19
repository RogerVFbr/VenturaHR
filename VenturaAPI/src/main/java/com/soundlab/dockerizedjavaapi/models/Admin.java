package com.soundlab.dockerizedjavaapi.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
@DiscriminatorValue(value="administrador")
public class Admin extends User {
}

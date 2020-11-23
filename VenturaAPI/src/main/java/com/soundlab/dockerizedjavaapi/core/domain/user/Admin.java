package com.soundlab.dockerizedjavaapi.core.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="administrador")
public class Admin extends User {
}

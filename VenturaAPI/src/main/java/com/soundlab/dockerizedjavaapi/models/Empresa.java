package com.soundlab.dockerizedjavaapi.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
@DiscriminatorValue(value="empresa")
public class Empresa extends User {
}

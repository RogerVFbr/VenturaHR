package com.soundlab.dockerizedjavaapi.core;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AuditableEntity {
    @Column(name = "date_created", insertable = false, updatable = false)
    @CreationTimestamp
    protected LocalDateTime dateCreated;

    @Column(name = "date_modified", insertable = false, updatable = true)
    @UpdateTimestamp
    protected LocalDateTime dateModified;
}

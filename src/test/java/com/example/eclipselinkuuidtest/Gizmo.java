package com.example.eclipselinkuuidtest;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gizmo")
public class Gizmo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gizmo_id")
    private UUID gizmoId;

    public Integer getId() {
        return id;
    }

    public UUID getGizmoId() {
        return gizmoId;
    }

    public void setGizmoId(UUID gizmoId) {
        this.gizmoId = gizmoId;
    }
}

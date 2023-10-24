package org.mmm.challengegrogurides.infrastructure.persistence.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity(name = "vehicles")
@Data
@AllArgsConstructor
public class VehicleDB {
    @Id
    @Column(name="id")
    UUID id;

    @Column(name="seats")
    private Integer seats;

    public VehicleDB() {}
}

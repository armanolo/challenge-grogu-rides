package org.mmm.challengegrogurides.infrastructure.persistence.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "rents")
@Data
@AllArgsConstructor
public class RentDB {
    @Id
    @Column(name="id")
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDB user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleDB vehicle;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @Column(name="return_time")
    private LocalDateTime returnTime;

    public RentDB() {}
}

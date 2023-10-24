package org.mmm.challengegrogurides.infrastructure.persistence.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity(name = "users")
@Data
@AllArgsConstructor
public class UserDB {
    @Id
    @Column(name="id")
    UUID id;

    @Column(name="dni", unique = true)
    private String dni;

    @Column(name="name")
    private String name;

    public UserDB() {}
}

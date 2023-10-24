package org.mmm.challengegrogurides.infrastructure.persistence.repositories;

import org.mmm.challengegrogurides.infrastructure.persistence.model.VehicleDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaVehicleRepository extends JpaRepository<VehicleDB, UUID> {

    //@Query("select c from City c where c.id = :id")
    Optional<VehicleDB> findById(UUID id);
}

package org.mmm.challengegrogurides.infrastructure.persistence.repositories;


import org.mmm.challengegrogurides.infrastructure.persistence.model.RentDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaRentRepository extends JpaRepository<RentDB, UUID> {

    /**
     *  WARNING: This is not the proper way to manage this behaviour for a BigDataBase
     */
    @Query(nativeQuery = true, value =
            "SELECT v.id FROM vehicles v LEFT JOIN "+
                    "(SELECT r.vehicle_id FROM rents r WHERE r.return_time IS NOT NULL) as wr " +
                    "ON wr.vehicle_id = v.id " +
                    "LEFT JOIN rents cr ON cr.vehicle_id = v.id AND cr.return_time IS NULL WHERE "+
                    "((wr.vehicle_id IS NOT NULL AND cr IS NULL) OR (wr.vehicle_id IS NULL AND cr.vehicle_id IS NULL)) " +
                    "AND v.seats >= :seats order by v.seats asc limit 1")
    UUID getNotRentVehicleIdOrderedByAvailableSeats(@Param("seats") Integer seats);
}
/*
            "SELECT v.id FROM vehicles v left join rents r on v.id = r.vehicle_id " +
                    "where (r.return_time is not null and v.seats >= :seats ) " +
                    "or (v.seats >= :seats and r is null) " +
                    "order by v.seats asc limit 1")

 */

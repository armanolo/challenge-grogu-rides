package org.mmm.challengegrogurides.domain.repository;

import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;

import java.util.List;
import java.util.Optional;


public interface RentRepository {
    void deleteAll();
    void createRent(RentVehicle rentVehicle);

    Optional<VehicleId> getNotRentedVehicleIdOrderedByAvailableSeats(AvailableSeats seats);

    List<RentVehicle> getRentList();

    void updateRent(RentVehicle currentRent);

    Optional<RentVehicle> getRentById(RentVehicleId rentVehicleId);
}

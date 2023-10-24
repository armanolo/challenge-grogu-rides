package org.mmm.challengegrogurides.domain.service;

import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.exception.NotFoundRentException;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;

import java.time.LocalDateTime;
import java.util.Optional;

public class DropOff {
    private final RentRepository rentRepository;

    public DropOff(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }

    public void execute(RentVehicleId rentVehicleId){
        Optional<RentVehicle> optionalRentVehicle = rentRepository.getRentById(rentVehicleId);
        if (optionalRentVehicle.isEmpty()){
            throw new NotFoundRentException("Rent not found");
        }

        RentVehicle currentRent = optionalRentVehicle.get();
        currentRent.setReturnTime(LocalDateTime.now());
        rentRepository.updateRent(currentRent);
    }
}

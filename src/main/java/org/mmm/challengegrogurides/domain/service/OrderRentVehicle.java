package org.mmm.challengegrogurides.domain.service;


import org.mmm.challengegrogurides.domain.OrderRent;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;

import java.util.Optional;
import java.util.UUID;

public class OrderRentVehicle {
    private final RentRepository rentRepository;

    public OrderRentVehicle(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public RentVehicle execute(OrderRent orderRent){
        Optional<VehicleId> optionalVehicleId = rentRepository
                .getNotRentedVehicleIdOrderedByAvailableSeats(orderRent.availableSeats());

        if (optionalVehicleId.isEmpty() ){
            return null;
        }
        RentVehicle newRentVehicle = new RentVehicle(new RentVehicleId(UUID.randomUUID().toString()),
                optionalVehicleId.get(),
                orderRent.userId(),
                orderRent.endRentTime(),
                null);
        rentRepository.createRent(newRentVehicle);
        return newRentVehicle;
    }
}

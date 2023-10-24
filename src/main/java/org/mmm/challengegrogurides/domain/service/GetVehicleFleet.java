package org.mmm.challengegrogurides.domain.service;

import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.repository.VehicleRepository;

import java.util.List;

public class GetVehicleFleet {

    private final VehicleRepository vehicleRepository;

    public GetVehicleFleet(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> execute(){
        return List.copyOf(vehicleRepository.getVehicles());
    }
}

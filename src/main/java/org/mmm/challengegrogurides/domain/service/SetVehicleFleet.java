package org.mmm.challengegrogurides.domain.service;

import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.exception.DuplicatedIdException;
import org.mmm.challengegrogurides.domain.exception.EmptyListException;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.domain.repository.UserRepository;
import org.mmm.challengegrogurides.domain.repository.VehicleRepository;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;

import java.util.HashMap;
import java.util.List;

public class SetVehicleFleet {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final RentRepository rentRepository;

    public SetVehicleFleet(VehicleRepository vehicleRepository, UserRepository userRepository, RentRepository rentRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
    }

    public void execute(List<Vehicle> vehicleList){
        if(vehicleList.isEmpty()){
            throw new EmptyListException("Not empty vehicle list allowed");
        }

        HashMap<VehicleId, Vehicle> vehicleHashMap = new HashMap<>();
        vehicleList.forEach(vehicle -> {
            if(vehicleHashMap.containsKey(vehicle.id())){
                throw new DuplicatedIdException("IDs are duplicated");
            }
            vehicleHashMap.put(vehicle.id(), vehicle);
        });

        rentRepository.deleteAll();
        userRepository.deleteAll();
        vehicleRepository.deleteVehicles();
        vehicleRepository.setVehicles(List.copyOf(vehicleHashMap.values()));
    }
}

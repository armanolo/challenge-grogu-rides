package org.mmm.challengegrogurides.domain.service;

import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.repository.RentRepository;

import java.util.List;

public class GetRentVehicle {
    private final RentRepository rentRepository;

    public GetRentVehicle(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }

    public List<RentVehicle> execute(){
        return rentRepository.getRentList();
    }
}

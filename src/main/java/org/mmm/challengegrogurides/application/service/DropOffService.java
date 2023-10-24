package org.mmm.challengegrogurides.application.service;

import org.mmm.challengegrogurides.domain.service.DropOff;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DropOffService {
    private final DropOff dropOff;


    @Autowired
    public DropOffService(DropOff dropOff){
        this.dropOff = dropOff;
    }

    public void execute(String rentVehicleId){
        dropOff.execute(new RentVehicleId(rentVehicleId));
    }
}

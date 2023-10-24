package org.mmm.challengegrogurides.domain.valueobject;

import org.mmm.challengegrogurides.domain.exception.InvalidRentVehicleIdException;

import java.util.UUID;

public class RentVehicleId extends Id{
    public RentVehicleId(String value) throws InvalidRentVehicleIdException {
        try{
            this.uuid = UUID.fromString(value);
        }catch(IllegalArgumentException e){
            throw new InvalidRentVehicleIdException(
                String.format("Invalid rent vehicle id: %s",value)
            );
        }
    }
}

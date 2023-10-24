package org.mmm.challengegrogurides.domain.valueobject;

import org.mmm.challengegrogurides.domain.exception.InvalidVehicleIdException;
import java.util.UUID;

public class VehicleId extends Id{
    public VehicleId(String value) throws InvalidVehicleIdException {
        try{
            this.uuid = UUID.fromString(value);
        }catch(IllegalArgumentException e){
            throw new InvalidVehicleIdException(
                String.format("Invalid Vehicle id: %s",value)
            );
        }
    }
}

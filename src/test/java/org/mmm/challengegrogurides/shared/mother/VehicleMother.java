package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;

import java.util.List;
import java.util.UUID;

public class VehicleMother extends CommonMother{

    public static AvailableSeats validAvailableSeats(){
        return new AvailableSeats(faker.number().numberBetween(1,7));
    }

    public static Vehicle validVehicle(){
        return new Vehicle(new VehicleId(UUID.randomUUID().toString()), validAvailableSeats());
    }

    public static Vehicle validVehicleWithUuid(UUID uuid){
        return new Vehicle(new VehicleId(uuid.toString()), validAvailableSeats());
    }

    public static Vehicle validVehicleWithUuidAndSeats(UUID uuid, int seats){
        return new Vehicle(new VehicleId(uuid.toString()), new AvailableSeats(seats));
    }

    public static List<Vehicle> listVehicleWithDuplicatedUuid(UUID uuid){
        return List.of(validVehicleWithUuid(uuid),validVehicleWithUuid(uuid));
    }

    public static List<Vehicle> listValidVehicles(){
        return List.of(validVehicle(),validVehicle());
    }
}

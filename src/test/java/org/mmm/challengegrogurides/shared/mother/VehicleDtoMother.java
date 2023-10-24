package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;

import java.util.List;
import java.util.UUID;

public class VehicleDtoMother extends CommonMother{
    static final int MAX_SEAT = 7;

    private static int validSeats(){
        return faker.number().numberBetween(1, MAX_SEAT);
    }

    private static int invalidSeats(){
        if (faker.random().nextBoolean()){
            return faker.number().numberBetween(-5, 1);
        }else{
            return faker.number().numberBetween(MAX_SEAT, 20);
        }
    }

    public static VehicleDto validVehicleDtoById(String uuid){
        return new VehicleDto(uuid,validSeats());
    }
    public static VehicleDto valid(){
        return validVehicleDtoById(UUID.randomUUID().toString());
    }
    public static VehicleDto invalidSeatsVehicleDto(){
        return new VehicleDto(UUID.randomUUID().toString(),invalidSeats());
    }

    public static VehicleDto invalidVehicleId(){
        return new VehicleDto("",1);
    }

    public static List<VehicleDto> listVehicleWithDuplicatedUuid(String uuid){
        return List.of(validVehicleDtoById(uuid), validVehicleDtoById(uuid));
    }

    public static List<VehicleDto> listValidVehicleDtos(){
        return List.of(valid(), valid());
    }
}

package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class RentVehicleDtoMother extends CommonMother{


    public static RentVehicleDto valid() {
        return getRentVehicleDto(null, UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                LocalDateTime.now().plus(70, ChronoUnit.MINUTES), null);
    }

    public static RentVehicleDto invalidId() {
        return getRentVehicleDto("1L-1L", UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                LocalDateTime.now().plus(70, ChronoUnit.MINUTES), null);
    }

    private static RentVehicleDto getRentVehicleDto(String id, String vehicleId, String userId,
                                                    LocalDateTime endTime, LocalDateTime returnTime) {
        if(id == null){
            id = UUID.randomUUID().toString();
        }
        return new RentVehicleDto(id, vehicleId, userId, endTime, returnTime);
    }

    public static RentVehicleDto validWithOrderRent(OrderRentDto orderRentDto) {
        return getRentVehicleDto(null, UUID.randomUUID().toString(), orderRentDto.userId(),
                orderRentDto.endTime(), null);
    }

    public static RentVehicleDto validWithOrderRentAndReturn(OrderRentDto orderRentDto) {
        return getRentVehicleDto(null, UUID.randomUUID().toString(), orderRentDto.userId(),
                orderRentDto.endTime(), LocalDateTime.now().plus(5,ChronoUnit.HOURS));
    }
}

package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.domain.OrderRent;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.domain.valueobject.ReturnTime;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;

import java.time.LocalDateTime;
import java.util.UUID;

public class RentVehicleMother extends CommonMother{

    public static RentVehicle valid() {
        return validWithOrder(OrderRentMother.valid());
    }

    public static RentVehicle validWithOrder(OrderRent orderRent) {
        return getRent(orderRent, null);
    }

    public static RentVehicle validReturned(OrderRent orderRent) {
        return getRent(orderRent, LocalDateTime.now().plusHours(1L));
    }

    public static RentVehicle getRent( OrderRent orderRent, LocalDateTime returnTime){
        return new RentVehicle(
                new RentVehicleId(UUID.randomUUID().toString()),
                new VehicleId(UUID.randomUUID().toString()),
                orderRent.userId(), orderRent.endRentTime(), new ReturnTime(returnTime));
    }
}

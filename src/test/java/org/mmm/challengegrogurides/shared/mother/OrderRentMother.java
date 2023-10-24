package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.domain.OrderRent;
import org.mmm.challengegrogurides.domain.valueobject.EndRentTime;
import org.mmm.challengegrogurides.domain.valueobject.UserId;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderRentMother extends CommonMother{

    public static OrderRent valid() {
        return new OrderRent(
                new UserId(UUID.randomUUID().toString()),
                new EndRentTime( LocalDateTime.now().plusHours(2L)),
                VehicleMother.validAvailableSeats()
        );
    }
}

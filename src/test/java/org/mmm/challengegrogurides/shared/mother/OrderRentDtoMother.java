package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class OrderRentDtoMother extends CommonMother{


    public static OrderRentDto valid() {
        return getOrderRentDto( UUID.randomUUID().toString(),
                LocalDateTime.now().plus(70, ChronoUnit.MINUTES), 5);
    }
    public static OrderRentDto invalidUserId() {
        return getOrderRentDto("SDSDL1", LocalDateTime.now(), 5);
    }

    public static OrderRentDto invalidEndTimeEmpty() {
        return getOrderRentDto(UUID.randomUUID().toString(), null, 5);
    }

    public static OrderRentDto invalidEndTimePreviousTime() {
        return getOrderRentDto(UUID.randomUUID().toString(),
                LocalDateTime.now().minus(1, ChronoUnit.DAYS), 5);
    }

    public static OrderRentDto invalidEndTimeWrongTime() {
        return getOrderRentDto(UUID.randomUUID().toString(),
                LocalDateTime.now().plus(50, ChronoUnit.MINUTES), 5);
    }

    private static OrderRentDto getOrderRentDto(String userId, LocalDateTime endTime, Integer seats) {

        return new OrderRentDto(userId, endTime, seats);
    }

}

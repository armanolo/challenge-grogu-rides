package org.mmm.challengegrogurides.application.service.mapper;

import org.mmm.challengegrogurides.domain.OrderRent;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.EndRentTime;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.domain.valueobject.ReturnTime;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;
import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class RentMapper {

    public OrderRent dtoToDomain(OrderRentDto orderRentDto) throws InvalidUserIdException {
        return new OrderRent(
                new UserId(orderRentDto.userId()),
                new EndRentTime(orderRentDto.endTime()),
                new AvailableSeats(orderRentDto.seats())
        );
    }

    public OrderRentDto domainToDto(OrderRent orderRent) throws InvalidUserIdException {
        return new OrderRentDto(
                orderRent.userId().value(),
                orderRent.endRentTime().value(),
                orderRent.availableSeats().value()
        );
    }

    public RentVehicle dtoToDomain(RentVehicleDto rentVehicleDto) throws InvalidUserIdException {
        return new RentVehicle(new RentVehicleId(rentVehicleDto.id()),
                new VehicleId(rentVehicleDto.vehicleId()),
                new UserId(rentVehicleDto.userId()),
                new EndRentTime(rentVehicleDto.endTime()),
                new ReturnTime(rentVehicleDto.returnTime()));
    }

    public RentVehicleDto domainToDto(RentVehicle rentVehicle) {
        LocalDateTime returnTime = rentVehicle.returnTime() == null ? null : rentVehicle.returnTime().value();
        return new RentVehicleDto(
                rentVehicle.id().value(),rentVehicle.vehicleId().value(), rentVehicle.userId().value(),
                rentVehicle.endRentTime().value(), returnTime
        );
    }

    public List<RentVehicleDto> domainsToDTOs(List<RentVehicle> rentVehicleList) {
        return rentVehicleList.stream().map(this::domainToDto).toList();
    }
}

package org.mmm.challengegrogurides.application.service.mapper;

import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleMapper {

    public Vehicle dtoToDomain(VehicleDto vehicleDto){
        return new Vehicle(new VehicleId(vehicleDto.id()),new AvailableSeats(vehicleDto.seats()));
    }

    public VehicleDto domainToDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.id().value(), vehicle.availableSeats().value());
    }

    public List<Vehicle> dtosToDomains(List<VehicleDto> vehicleDtoList) {
        return vehicleDtoList.stream().map(this::dtoToDomain).toList();
    }


    public List<VehicleDto> domainsToDTOs(List<Vehicle> vehicleList) {
        return vehicleList.stream().map(this::domainToDto).toList();
    }
}

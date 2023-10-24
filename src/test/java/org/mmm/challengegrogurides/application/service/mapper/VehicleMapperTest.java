package org.mmm.challengegrogurides.application.service.mapper;

import org.junit.jupiter.api.Test;
import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.mmm.challengegrogurides.shared.mother.VehicleDtoMother;
import org.mmm.challengegrogurides.shared.mother.VehicleMother;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleMapperTest {

    VehicleMapper vehicleMapper = new VehicleMapper();

    @Test
    void dtoToDomain() {
        VehicleDto vehicleDto = VehicleDtoMother.valid();
        Vehicle vehicle = vehicleMapper.dtoToDomain(vehicleDto);
        assertEquals(vehicle.id().value(),vehicleDto.id());
        assertEquals(vehicle.availableSeats().value(),vehicleDto.seats());
    }

    @Test
    void domainToDto() {
        Vehicle vehicle = VehicleMother.validVehicle();
        VehicleDto vehicleDto = vehicleMapper.domainToDto(vehicle);
        assertEquals(vehicleDto.id(),vehicle.id().value());
        assertEquals(vehicleDto.seats(),vehicle.availableSeats().value());
    }

    @Test
    void dtosToDomains() {
        List<VehicleDto> vehicleDtoList = VehicleDtoMother.listValidVehicleDtos();
        List<Vehicle> vehicleList = vehicleMapper.dtosToDomains(vehicleDtoList);
        assertEquals(vehicleList.size(), vehicleDtoList.size());
    }

    @Test
    void domainsToDTOs() {
        List<Vehicle> vehicleList = VehicleMother.listValidVehicles();
        List<VehicleDto> vehicleDtoList = vehicleMapper.domainsToDTOs(vehicleList);
        assertEquals(vehicleDtoList.size(), vehicleList.size());
    }
}

package org.mmm.challengegrogurides.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.mapper.VehicleMapper;
import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.exception.InvalidAvailableSeats;
import org.mmm.challengegrogurides.domain.exception.InvalidVehicleIdException;
import org.mmm.challengegrogurides.domain.service.SetVehicleFleet;
import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.mmm.challengegrogurides.shared.mother.VehicleDtoMother;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SetVehicleFleetServiceTest {

    SetVehicleFleetService setVehicleFleetService;

    @Mock
    SetVehicleFleet setVehicleFleet;

    @InjectMocks
    VehicleMapper vehicleMapper;

    @BeforeEach
    void setup(){
        setVehicleFleetService = new SetVehicleFleetService(setVehicleFleet, vehicleMapper);
    }

    @Test
    void execute() {
        List<VehicleDto> vehicleDtoList = VehicleDtoMother.listValidVehicleDtos();
        List<Vehicle> vehicleList = vehicleMapper.dtosToDomains(vehicleDtoList);
        setVehicleFleetService.execute(vehicleDtoList);
        then(setVehicleFleet).should(times(1)).execute(vehicleList);
    }

    @Test
    void throw_error_invalid_vehicle_id() {
        List<VehicleDto> vehicleDtoList = List.of(VehicleDtoMother.invalidVehicleId());
        String id = vehicleDtoList.get(0).id();

        InvalidVehicleIdException exceptionThrown = assertThrows(
                InvalidVehicleIdException.class,
                () -> setVehicleFleetService.execute(vehicleDtoList)
        );
        String expectedMessage = String.format("Invalid Vehicle id: %s",id);
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(setVehicleFleet).should(times(0)).execute(any());
    }
    @Test
    void throw_error_invalid_seats() {
        List<VehicleDto> vehicleDtoList = List.of(VehicleDtoMother.invalidSeatsVehicleDto());
        int seats = vehicleDtoList.get(0).seats();

        InvalidAvailableSeats exceptionThrown = assertThrows(
                InvalidAvailableSeats.class,
                () -> setVehicleFleetService.execute(vehicleDtoList)
        );
        AvailableSeats availableSeats = new AvailableSeats(5);
        String expectedMessage = String.format(
                "Invalid seats: got %d when minimum is %d and the maximum is %d",
                seats, availableSeats.getMinimumValidSeat(), availableSeats.getMaxValidSeat()
        );
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(setVehicleFleet).should(times(0)).execute(any());
    }
}

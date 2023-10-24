package org.mmm.challengegrogurides.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.mapper.VehicleMapper;
import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.service.GetVehicleFleet;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.mmm.challengegrogurides.shared.mother.VehicleDtoMother;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVehicleFleetServiceTest {

    GetVehicleFleetService getVehicleFleetService;

    @Mock
    GetVehicleFleet getVehicleFleet;

    @InjectMocks
    VehicleMapper vehicleMapper;

    @BeforeEach
    void setup(){
        getVehicleFleetService = new GetVehicleFleetService(getVehicleFleet, vehicleMapper);
    }

    @Test
    void execute() {
        List<VehicleDto> vehicleDtoList = VehicleDtoMother.listValidVehicleDtos();
        String firstUUID = vehicleDtoList.get(0).id();
        List<Vehicle> vehicleList = vehicleMapper.dtosToDomains(vehicleDtoList);
        when(getVehicleFleet.execute()).thenReturn(vehicleList);

        List<VehicleDto> vehicleListOut = getVehicleFleetService.execute();

        then(getVehicleFleet).should(times(1)).execute();
        Assertions.assertEquals(2,vehicleListOut.size());
        vehicleListOut.stream().findFirst()
                .ifPresent(vehicle ->
                        Assertions.assertEquals(firstUUID, vehicle.id())
                );

    }
}

package org.mmm.challengegrogurides.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.repository.VehicleRepository;
import org.mmm.challengegrogurides.shared.mother.VehicleMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVehicleFleetTest {

    @Mock
    VehicleRepository vehicleRepository;
    GetVehicleFleet getVehicleFleet;

    @BeforeEach
    void setup(){
        getVehicleFleet = new GetVehicleFleet(vehicleRepository);
    }

    @Test
    void execute_with_right_result() {
        List<Vehicle> vehicleList = VehicleMother.listValidVehicles();
        String firstUUID = vehicleList.get(0).id().toString();
        when(vehicleRepository.getVehicles()).thenReturn(vehicleList);
        List<Vehicle> vehicleListOut = getVehicleFleet.execute();
        then(vehicleRepository).should(times(1)).getVehicles();
        Assertions.assertEquals(2,vehicleListOut.size());
        vehicleListOut.stream().findFirst()
                .ifPresent(vehicle ->
                        Assertions.assertEquals(firstUUID, vehicle.id().toString())
                );

    }
}

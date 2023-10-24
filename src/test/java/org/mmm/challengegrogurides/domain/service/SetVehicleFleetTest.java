package org.mmm.challengegrogurides.domain.service;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.exception.DuplicatedIdException;
import org.mmm.challengegrogurides.domain.exception.EmptyListException;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.domain.repository.UserRepository;
import org.mmm.challengegrogurides.domain.repository.VehicleRepository;
import org.mmm.challengegrogurides.shared.mother.VehicleMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SetVehicleFleetTest {

    @Mock
    VehicleRepository vehicleRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    RentRepository rentRepository;
    SetVehicleFleet setVehicleFleet;

    @BeforeEach
    void setup(){
        setVehicleFleet = new SetVehicleFleet(vehicleRepository, userRepository, rentRepository);
    }

    @Test
    void execute_with_right_result() {
        List<Vehicle> vehicleList = VehicleMother.listValidVehicles();
        setVehicleFleet.execute(vehicleList);
        then(rentRepository).should(times(1)).deleteAll();
        then(userRepository).should(times(1)).deleteAll();
        then(vehicleRepository).should(times(1)).deleteVehicles();
        then(vehicleRepository).should(times(1)).setVehicles(anyList());
    }

    @Test
    void execute_and_throw_duplicated_id() {
        UUID uuidDuplicated = UUID.randomUUID();
        List<Vehicle> vehicleList = VehicleMother.listVehicleWithDuplicatedUuid(uuidDuplicated);
        assertThatThrownBy(
                () -> setVehicleFleet.execute(vehicleList))
                .isInstanceOf(DuplicatedIdException.class)
                .hasMessage("IDs are duplicated");
        then(rentRepository).should(never()).deleteAll();
        then(userRepository).should(never()).deleteAll();
        then(vehicleRepository).should(never()).deleteVehicles();
        then(vehicleRepository).should(never()).setVehicles(any());
    }

    @Test
    void execute_and_throw_empty_list() {
        List<Vehicle> vehicleList = List.of();
        assertThatThrownBy(
                () -> setVehicleFleet.execute(vehicleList))
                .isInstanceOf(EmptyListException.class)
                .hasMessage("Not empty vehicle list allowed");
        then(rentRepository).should(never()).deleteAll();
        then(userRepository).should(never()).deleteAll();
        then(vehicleRepository).should(never()).deleteVehicles();
        then(vehicleRepository).should(never()).setVehicles(any());
    }
}

package org.mmm.challengegrogurides.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.shared.mother.RentVehicleMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRentVehicleTest {

    @Mock
    RentRepository rentRepository;
    GetRentVehicle getRentVehicle;

    @BeforeEach
    void setup(){
        getRentVehicle = new GetRentVehicle(rentRepository);
    }

    @Test
    void execute_with_right_result() {
        List<RentVehicle> rentVehicleList = List.of(RentVehicleMother.valid(), RentVehicleMother.valid());
        when(rentRepository.getRentList()).thenReturn(rentVehicleList);
        getRentVehicle.execute();
        verify(rentRepository,times(1)).getRentList();
    }
}

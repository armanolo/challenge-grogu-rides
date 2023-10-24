package org.mmm.challengegrogurides.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.OrderRent;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.shared.mother.OrderRentMother;
import org.mmm.challengegrogurides.shared.mother.RentVehicleMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

@ExtendWith(MockitoExtension.class)
class OrderRentVehicleTest {

    @Mock
    RentRepository rentRepository;
    OrderRentVehicle orderRentVehicle;

    @BeforeEach
    void setup(){
        orderRentVehicle = new OrderRentVehicle(rentRepository);
    }

    @Test
    void execute_with_right_result() {
        OrderRent orderRent = OrderRentMother.valid();
        RentVehicle rentVehicle = RentVehicleMother.validWithOrder(orderRent);
        given(rentRepository.getNotRentedVehicleIdOrderedByAvailableSeats(orderRent.availableSeats()))
                .willReturn(Optional.of(rentVehicle.vehicleId()));
        orderRentVehicle.execute(orderRent);
        then(rentRepository).should(times(1)).createRent(
                argThat( p ->
                        p.userId().equals(rentVehicle.userId()) &&
                        p.vehicleId().equals(rentVehicle.vehicleId()) &&
                        p.endRentTime().equals(rentVehicle.endRentTime())
                )
        );
    }
}

package org.mmm.challengegrogurides.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.exception.NotFoundRentException;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.shared.mother.RentVehicleMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DropOffTest {
    @Mock
    RentRepository rentRepository;
    DropOff dropOff;

    @BeforeEach
    void setup(){
        dropOff = new DropOff(rentRepository);
    }

    @Test
    void execute_with_right_result() {
        RentVehicle rentVehicle = RentVehicleMother.valid();
        Optional<RentVehicle> optionalRentVehicle = Optional.of(rentVehicle);
        when(rentRepository.getRentById(rentVehicle.id())).thenReturn(optionalRentVehicle);
        dropOff.execute(rentVehicle.id());
        verify(rentRepository, times(1)).updateRent(rentVehicle);
    }

    @Test
    void throw_error_user_with_not_found() {
        RentVehicle rentVehicle = RentVehicleMother.valid();
        when(rentRepository.getRentById(rentVehicle.id())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> dropOff.execute(rentVehicle.id()))
                .isInstanceOf(NotFoundRentException.class)
                .hasMessage("Rent not found");
        verify(rentRepository, times(0)).updateRent(any());
    }
}

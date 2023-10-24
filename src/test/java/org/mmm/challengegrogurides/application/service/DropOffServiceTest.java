package org.mmm.challengegrogurides.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.exception.InvalidRentVehicleIdException;
import org.mmm.challengegrogurides.domain.service.DropOff;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.shared.mother.RentVehicleMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DropOffServiceTest {

    @Mock
    DropOff dropOff;

    DropOffService dropOffService;

    @BeforeEach
    void setup(){
        dropOffService = new DropOffService(dropOff);
    }

    @Test
    void execute() {
        RentVehicleId rentVehicleId = RentVehicleMother.valid().id();
        String strRentVehicleId = rentVehicleId.value();
        dropOffService.execute(strRentVehicleId);
        verify(dropOff,times(1)).execute(rentVehicleId);
    }

    @Test
    void throw_error_invalid_user_id() {
        String id = " ";
        InvalidRentVehicleIdException exceptionThrown = assertThrows(
                InvalidRentVehicleIdException.class,
                () -> dropOffService.execute(id)
        );
        String expectedMessage = String.format("Invalid rent vehicle id: %s",id);
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        verify(dropOff,times(0)).execute(any());
    }
}

package org.mmm.challengegrogurides.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.mapper.RentMapper;
import org.mmm.challengegrogurides.domain.OrderRent;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.exception.InvalidEndTimeException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.service.OrderRentVehicle;
import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.mmm.challengegrogurides.shared.mother.OrderRentDtoMother;
import org.mmm.challengegrogurides.shared.mother.RentVehicleDtoMother;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

@ExtendWith(MockitoExtension.class)
class OrderRentVehicleServiceTest {

    OrderRentVehicleService orderRentVehicleService;

    @Mock
    OrderRentVehicle orderRentVehicle;

    @InjectMocks
    RentMapper rentMapper;

    @BeforeEach
    void setup(){
        orderRentVehicleService = new OrderRentVehicleService(orderRentVehicle, rentMapper);
    }

    @Test
    void execute() {
        OrderRentDto orderRentDto = OrderRentDtoMother.valid();
        RentVehicleDto rentVehicleDtoExpected = RentVehicleDtoMother.validWithOrderRent(orderRentDto);
        RentVehicle rentVehicle = rentMapper.dtoToDomain(rentVehicleDtoExpected);
        OrderRent orderRent = rentMapper.dtoToDomain(orderRentDto);
        given(orderRentVehicle.execute(orderRent)).willReturn(rentVehicle);

        RentVehicleDto rentVehicleDto = orderRentVehicleService.execute(orderRentDto);
        then(orderRentVehicle).should(times(1)).execute(orderRent);
        assertEquals(rentVehicleDto, rentVehicleDtoExpected);
    }

    @Test
    void throw_error_invalid_user_id() {
        OrderRentDto orderRentDto = OrderRentDtoMother.invalidUserId();
        String id = orderRentDto.userId();

        InvalidUserIdException exceptionThrown = assertThrows(
                InvalidUserIdException.class,
                () -> orderRentVehicleService.execute(orderRentDto)
        );
        String expectedMessage = String.format("Invalid user id: %s",id);
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(orderRentVehicle).should(times(0)).execute(any());
    }

    @Test
    void throw_error_invalid_end_time_empty() {
        OrderRentDto orderRentDto = OrderRentDtoMother.invalidEndTimeEmpty();
        String id = orderRentDto.userId();

        InvalidEndTimeException exceptionThrown = assertThrows(
                InvalidEndTimeException.class,
                () -> orderRentVehicleService.execute(orderRentDto)
        );
        assertEquals("Invalid time", exceptionThrown.getMessage());
        then(orderRentVehicle).should(times(0)).execute(any());
    }

    @Test
    void throw_error_invalid_previous_end_time() {
        OrderRentDto orderRentDto = OrderRentDtoMother.invalidEndTimePreviousTime();
        String id = orderRentDto.userId();

        InvalidEndTimeException exceptionThrown = assertThrows(
                InvalidEndTimeException.class,
                () -> orderRentVehicleService.execute(orderRentDto)
        );
        assertEquals("The time selected has passed", exceptionThrown.getMessage());
        then(orderRentVehicle).should(times(0)).execute(any());
    }

    @Test
    void throw_error_invalid_wrong_end_time() {
        OrderRentDto orderRentDto = OrderRentDtoMother.invalidEndTimeWrongTime();

        InvalidEndTimeException exceptionThrown = assertThrows(
                InvalidEndTimeException.class,
                () -> orderRentVehicleService.execute(orderRentDto)
        );
        String expectedMessage = "Duration minimum is one hour not 49 minutes";
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(orderRentVehicle).should(times(0)).execute(any());
    }
}

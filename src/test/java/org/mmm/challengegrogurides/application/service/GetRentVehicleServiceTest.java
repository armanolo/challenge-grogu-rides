package org.mmm.challengegrogurides.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.mapper.RentMapper;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.service.GetRentVehicle;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.mmm.challengegrogurides.shared.mother.RentVehicleMother;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetRentVehicleServiceTest {

    GetRentVehicleService getRentVehicleService;

    @Mock
    GetRentVehicle getRentVehicle;

    @InjectMocks
    RentMapper rentMapper;

    @BeforeEach
    void setup(){
        getRentVehicleService = new GetRentVehicleService(getRentVehicle, rentMapper);
    }

    @Test
    void execute() {
        List<RentVehicle> rentVehicleList = List.of(RentVehicleMother.valid(),RentVehicleMother.valid());
        List<RentVehicleDto> rentVehicleDtoExpected = rentMapper.domainsToDTOs(rentVehicleList);
        given(getRentVehicle.execute()).willReturn(rentVehicleList);
        List<RentVehicleDto> rentVehicleDto = getRentVehicleService.execute();
        then(getRentVehicle).should(times(1)).execute();
        assertEquals(rentVehicleDto, rentVehicleDtoExpected);
    }
}

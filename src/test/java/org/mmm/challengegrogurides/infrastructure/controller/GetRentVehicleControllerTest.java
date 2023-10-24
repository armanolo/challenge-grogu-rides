package org.mmm.challengegrogurides.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.GetRentVehicleService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.mmm.challengegrogurides.shared.mother.RentVehicleDtoMother;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GetRentVehicleController.class)
class GetRentVehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetRentVehicleService getRentVehicleService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getRentList() throws Exception {
        List<RentVehicleDto> rentVehicleDtoList = List.of(
                RentVehicleDtoMother.valid(), RentVehicleDtoMother.valid()
        );
        String jsonExpected = objectMapper.writeValueAsString(rentVehicleDtoList);

        given(getRentVehicleService.execute()).willReturn(rentVehicleDtoList);
        mockMvc.perform(get("/rent"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonExpected));
        verify(getRentVehicleService,times(1)).execute();
    }
}

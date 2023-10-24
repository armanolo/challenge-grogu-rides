package org.mmm.challengegrogurides.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.GetVehicleFleetService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.mmm.challengegrogurides.shared.mother.VehicleDtoMother;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GetVehicleFleetController.class)
class GetVehicleFleetControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetVehicleFleetService getVehicleFleetService;

    @Test
    void getVehicles() throws Exception {

        List<VehicleDto> vehicleDtoList = VehicleDtoMother.listValidVehicleDtos();
        String id1 = vehicleDtoList.get(0).id();
        String id2 = vehicleDtoList.get(1).id();
        int seats1 = vehicleDtoList.get(0).seats();
        int seats2 = vehicleDtoList.get(1).seats();

        when(getVehicleFleetService.execute()).thenReturn(vehicleDtoList);

        mockMvc.perform(
                get("/vehicles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id1))
                .andExpect(jsonPath("$[0].seats").value(seats1))
                .andExpect(jsonPath("$[1].id").value(id2))
                .andExpect(jsonPath("$[1].seats").value(seats2));
        verify(getVehicleFleetService,times(1)).execute();
    }
}

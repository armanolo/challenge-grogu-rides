package org.mmm.challengegrogurides.infrastructure.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.SetVehicleFleetService;
import org.mmm.challengegrogurides.domain.exception.DuplicatedIdException;
import org.mmm.challengegrogurides.domain.exception.EmptyListException;
import org.mmm.challengegrogurides.domain.exception.InvalidAvailableSeats;
import org.mmm.challengegrogurides.domain.exception.InvalidVehicleIdException;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SetVehicleFleetController.class)
class SetVehicleFleetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SetVehicleFleetService setVehicleFleetService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void postVehicles() throws Exception {

        String jsonFormatted = getJsonVehicle();

        mockMvc.perform(
                    post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isOk());
        verify(setVehicleFleetService,times(1)).execute(any());
    }


    @Test
    void postVehicles_ThrowErrorInvalidBody() throws Exception {
        String jsonFormatted = "[]";

        doThrow(new EmptyListException("Not empty vehicle list allowed")).when(setVehicleFleetService).execute(Mockito.anyList());

        mockMvc.perform(
                post("/vehicles")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonFormatted)
                ).andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", is("Not empty vehicle list allowed")));
        verify(setVehicleFleetService,times(1)).execute(any());
    }

    @Test
    void postVehicles_ThrowErrorByDuplicityId() throws Exception {
        String jsonFormatted = getJsonVehicle();

        doThrow(new DuplicatedIdException("IDs are duplicated")).when(setVehicleFleetService).execute(Mockito.anyList());

        mockMvc.perform(
                    post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("IDs are duplicated")));
        verify(setVehicleFleetService,times(1)).execute(any());
    }

    @Test
    void postVehicles_ThrowErrorIllegalId() throws Exception {
        String jsonFormatted = getJsonVehicle();
        List<VehicleDto> vehicleDtoList = objectMapper.readValue(jsonFormatted, new TypeReference<>() {});

        String messageException = "Invalid Vehicle id: 1L";
        doThrow(new InvalidVehicleIdException(messageException)).when(setVehicleFleetService)
                .execute(vehicleDtoList);

        mockMvc.perform(
                    post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error",
                        is("Invalid Vehicle id: 1L")
                ));
        verify(setVehicleFleetService,times(1)).execute(any());
    }

    @Test
    void postVehicles_ThrowErrorInvalidAvailableSeats() throws Exception {
        String jsonFormatted = getJsonVehicle();
        List<VehicleDto> vehicleDtoList = objectMapper.readValue(jsonFormatted, new TypeReference<>() {});


        String messageException = "Invalid seats: got 8 when minimum is 0 and the maximum is 6";
        doThrow(new InvalidAvailableSeats(messageException)).when(setVehicleFleetService)
                .execute(vehicleDtoList);

        mockMvc.perform(
                    post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error",
                        is(messageException)
                ));
        verify(setVehicleFleetService,times(1)).execute(any());
    }

    private String getJsonVehicle(){
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String jsonVehicle = "[{\"id\":\"%s\",\"seats\":4},{\"id\":\"%s\",\"seats\":6}]";
        return String.format(jsonVehicle, id1, id2);
    }
}

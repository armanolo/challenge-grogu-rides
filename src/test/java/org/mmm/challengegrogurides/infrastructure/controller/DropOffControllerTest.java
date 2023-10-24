package org.mmm.challengegrogurides.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.DropOffService;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.exception.NotFoundRentException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DropOffController.class)
class DropOffControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DropOffService dropOffService;

    private final String rentVehicleId = UUID.randomUUID().toString();

    @Test
    void dropOff() throws Exception {
        mockMvc.perform(
                post("/rent/"+rentVehicleId+"/drop-off")
        ).andExpect(status().isOk());
        verify(dropOffService,times(1)).execute(rentVehicleId);
    }

    @Test
    void postUser_ThrowErrorInvalidRentVehicleId() throws Exception {
        String message = "Invalid rent vehicle id: ";
        doThrow(new InvalidUserIdException(message)).when(dropOffService).execute(Mockito.any());

        mockMvc.perform(
                        post("/rent/ss/drop-off")
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(message)));
        verify(dropOffService,times(1)).execute("ss");
    }

    @Test
    void postUser_ThrowErrorNotFoundRentVehicle() throws Exception {
        String message = "Rent not found";
        doThrow(new NotFoundRentException(message)).when(dropOffService).execute(Mockito.any());

        mockMvc.perform(
                        post("/rent/"+rentVehicleId+"/drop-off")
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is(message)));
        verify(dropOffService,times(1)).execute(rentVehicleId);
    }
}

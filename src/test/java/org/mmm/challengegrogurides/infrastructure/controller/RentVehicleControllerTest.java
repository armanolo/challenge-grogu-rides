package org.mmm.challengegrogurides.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.OrderRentVehicleService;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.mmm.challengegrogurides.shared.mother.RentVehicleDtoMother;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RentVehicleController.class)
class RentVehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRentVehicleService orderRentVehicleService;

    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void orderRent() throws Exception {
        String jsonFormatted = getJsonOrderRent();
        OrderRentDto orderRentDto = objectMapper.readValue(jsonFormatted, OrderRentDto.class);
        RentVehicleDto rentVehicleDto = RentVehicleDtoMother.valid();

        when(orderRentVehicleService.execute(orderRentDto)).thenReturn(rentVehicleDto);

        mockMvc.perform(
                    post("/rent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isOk());
    }

    @Test
    void notRented() throws Exception {
        String jsonFormatted = getJsonOrderRent();
        OrderRentDto orderRentDto = objectMapper.readValue(jsonFormatted, OrderRentDto.class);

        when(orderRentVehicleService.execute(orderRentDto)).thenReturn(null);

        mockMvc.perform(
                    post("/rent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isAccepted());
    }

    @Test
    void postOrderRent_ThrowErrorInvalidUserId() throws Exception {
        String jsonFormatted = "{}";

        doThrow(new InvalidUserIdException("Invalid user id: ")).when(orderRentVehicleService).execute(any());

        mockMvc.perform(
                        post("/rent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonFormatted)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Invalid user id: ")));
        verify(orderRentVehicleService,times(1)).execute(any());
    }

    public String getJsonOrderRent(){
        String userId = UUID.randomUUID().toString();
        String endTime = LocalDateTime.now().format(CUSTOM_FORMATTER);
        int seats = 2;
        String jsonUser = "{\"userId\":\"%s\",\"endTime\":\"%s\",\"seats\":\"%d\"}";
        return String.format(jsonUser, userId, endTime, seats);
    }
}

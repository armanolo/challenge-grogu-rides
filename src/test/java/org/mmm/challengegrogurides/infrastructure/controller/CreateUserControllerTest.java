package org.mmm.challengegrogurides.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.CreateUserService;
import org.mmm.challengegrogurides.domain.exception.InvalidDniException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserNameException;
import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
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
@WebMvcTest(controllers = CreateUserController.class)
class CreateUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService setUserService;

    @Test
    void createUser() throws Exception {
        String jsonFormatted = getJsonUser();

        mockMvc.perform(
                    post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isCreated());
        verify(setUserService,times(1)).execute(any());
    }


    @Test
    void postUser_ThrowErrorInvalidUserId() throws Exception {
        String jsonFormatted = "{}";

        doThrow(new InvalidUserIdException("Invalid user id: ")).when(setUserService).execute(Mockito.any());

        mockMvc.perform(
                post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonFormatted)
                ).andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", is("Invalid user id: ")));
        verify(setUserService,times(1)).execute(any());
    }

    @Test
    void postUser_ThrowErrorInvalidDni() throws Exception {
        String jsonFormatted = getJsonUser();

        doThrow(new InvalidDniException("Dni has not the format: "+ Dni.FORMAT_DNI))
                .when(setUserService).execute(Mockito.any());

        mockMvc.perform(
                    post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Dni has not the format: "+Dni.FORMAT_DNI)));
        verify(setUserService,times(1)).execute(any());
    }

    @Test
    void postUser_ThrowErrorInvalidUserName() throws Exception {
        String jsonFormatted = getJsonUser();

        doThrow(new InvalidUserNameException("Invalid name"))
                .when(setUserService).execute(Mockito.any());

        mockMvc.perform(
                    post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFormatted)
                    ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Invalid name")));
        verify(setUserService,times(1)).execute(any());
    }

    private String getJsonUser(){
        String id = UUID.randomUUID().toString();
        String dni = "77968070W";
        String name = "Joaquin Gonzalez";
        String jsonUser = "{\"id\":\"%s\",\"dni\":\"%s\",\"name\":\"%s\"}";
        return  String.format(jsonUser, id, dni, name);
    }
}

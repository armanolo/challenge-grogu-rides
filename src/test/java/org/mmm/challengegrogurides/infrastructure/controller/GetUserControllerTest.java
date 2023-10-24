package org.mmm.challengegrogurides.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.GetUserService;
import org.mmm.challengegrogurides.domain.exception.DuplicatedDniException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.exception.NotFoundUserException;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.mmm.challengegrogurides.shared.mother.UserDtoMother;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GetUserController.class)
class GetUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getUser() throws Exception {
        String uuid = UUID.randomUUID().toString();
        UserDto userDto = UserDtoMother.valid();

        String jsonExpected = objectMapper.writeValueAsString(userDto);
        given(getUserService.execute(uuid)).willReturn(userDto);
        mockMvc.perform(get("/user/"+uuid))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonExpected));
        verify(getUserService,times(1)).execute(uuid);
    }

    @Test
    void postUser_ThrowErrorInvalidUserId() throws Exception {
        String id = "1LL";
        doThrow(new InvalidUserIdException("Invalid user id: ")).when(getUserService).execute(id);
        mockMvc.perform(
                        get("/user/"+id)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Invalid user id: ")));
        verify(getUserService,times(1)).execute(id);
    }

    @Test
    void postUser_ThrowErrorNotFoundUser() throws Exception {
        String id = UUID.randomUUID().toString();
        String errorExpected = String.format("Not found user with id %s",id);
        doThrow(new NotFoundUserException(errorExpected)).when(getUserService).execute(id);
        mockMvc.perform(
                        get("/user/"+id)
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is(errorExpected)));
        verify(getUserService,times(1)).execute(id);
    }

    @Test
    void postUser_ThrowErrorDniDuplicate() throws Exception {
        String dni = "27650297L";
        String errorExpected = String.format("Duplicated dni: %s",dni);
        String id = UUID.randomUUID().toString();
        doThrow(new DuplicatedDniException(errorExpected)).when(getUserService).execute(id);
        mockMvc.perform(
                        get("/user/"+id)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(errorExpected)));
        verify(getUserService,times(1)).execute(id);
    }
}
